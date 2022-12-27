package mrs;

import org.ejml.simple.SimpleMatrix;

import static java.lang.Math.*;
import static java.lang.Math.abs;

public class Solution{
    private double [] t;
    private int n;
    private double t0=0.0;
    private static String error;
    private double tn=1.0;
    private double h;
    public void setN(int n){
        this.t=t(t0,tn,n);
        this.n=t.length;
        this.h = (t[n-1]-t[0])/(double)this.n;
    }
    public double[] t(double t0,double tn,int n){
        double[] t = new double[n+1];
        for(int i=0;i<n+1;i++){
            t[i]=t0+(tn-t0)*(float)i/(float)n;
        }
        return t;
    }
    public double f (double x){
        return pow(x,3)+x;
    }
public double[] accurate(){
    double [] u = new double[n];
    for(int i=0;i<n;i++){
        u[i]=(-25.0/24.0/exp(2.0))*exp(2*t[i])+(-25.0/12.0/exp(2.0)+7.0/4.0)*exp(-t[i])+ pow(t[i],3)/2.0-3.0* pow(t[i],2)/4.0+11.0*t[i]/4.0-17.0/8.0;
    }
    return u;
}
    public double[] solver(){
        SimpleMatrix a=matrix();
        SimpleMatrix f=ftab();
        SimpleMatrix u = null;
        u=a.mult(f);
        double[] result = new double[n];
        for (int i =0;i<n;i++){
            result[i]=u.get(i,0);
        }
        return result;
    }
    public SimpleMatrix matrix(){
        SimpleMatrix matrix =new SimpleMatrix(n,n);
        matrix.set(0,0,2.0/pow(h,2)+2.0);
        matrix.set(0,1,-2.0/pow(h,2));
        for (int i =1;i<n-1;i++){
            matrix.set(i,i-1,-1.0/pow(h,2)-1.0/(2.0*h));
            matrix.set(i,i,2.0/pow(h,2)+2.0);
            matrix.set(i,i+1,-1.0/pow(h,2)+1.0/(2.0*h));
        }
        matrix.set(n-1,n-2,-2.0/pow(h,2));
        matrix.set(n-1,n-1,2.0/pow(h,2)+2.0/h+1.0);
        SimpleMatrix S = matrix.invert();
        return S;
    }
    public SimpleMatrix ftab(){
        SimpleMatrix ftab =new SimpleMatrix(n,1);
        ftab.set(0,0,-1.0-(2.0/h));
        for (int i =1;i<n;i++){
            ftab.set(i,0,f(t[i]));
        }
        return ftab;
    }
    double error(double[] accurate, double[] result){
        int n = result.length;
        double error = 0.0;
        for(int i = 0; i < n; i++ ){
            if (abs(result[i]-accurate[i])>error)
                error=abs(result[i]-accurate[i]);
        }
        return error;
    }
    public String getError(){
        return this.error;
    }
    public void run(){
        double [] sol1 = accurate();
        double [] sol2 = solver();
        Plot plot=new Plot(sol1,sol2,t);
        plot.plot();
        error =String.valueOf(error(sol1,sol2));
    }
}
