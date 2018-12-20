/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author admin
 */
public class Program 
{
    Details dt=new Details();
    
    Program()
    {
        
    }
    
     public void find()
    {
        try
        {
            DecimalFormat df=new DecimalFormat("#.###");
            dt.Q=new double[dt.Agent][dt.State];
            dt.UAP=new double[dt.Agent][dt.State];
            
            // Initialize Q 
            for(int i=0;i<dt.Agent;i++)
            {
                for(int j=0;j<dt.State;j++)
                {                
                    dt.Q[i][j]=0;
                    dt.UAP[i][j]=0;                   
                }
            }
            Random rn=new Random();
            int Step=0;
            for(int i=0;i<dt.Agent;i++)
            {
                int st1=rn.nextInt(dt.State);
                while(st1!=dt.StateGoal)
                {
                    Step=Step+1;
                    int index=rn.nextInt(dt.State);
                              
                    int next=index;
                    int act=next;
                    
                    double q = (1-dt.alpha)*dt.Q[i][st1];
                    double r = dt.w1+dt.w2+(dt.w3*dt.R[i][st1]);
                    double value = q + dt.alpha* (r + dt.gamma - q);
                    dt.Q[i][st1]=value;            
                        
                    st1=next;
                }
                
                String res="";
                for(int j=0;j<dt.State;j++)
                {
                    //System.out.print(df.format(dt.Q[i][j])+" ");                   
                    res=res+df.format(dt.Q[i][j])+"#";
                }
                    
                    
                System.out.println("============================== "+(i+1)+" = "+res);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
