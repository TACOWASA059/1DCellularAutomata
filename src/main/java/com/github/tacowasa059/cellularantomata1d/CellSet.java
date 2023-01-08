package com.github.tacowasa059.cellularantomata1d;


import org.bukkit.Location;
import org.bukkit.Material;

public class CellSet {
    static private final int r=8;
    private int[] rule=new int[r];
    private int n;
    private int tmax;
    private Location location;
    private int[] cell_list;
    private int t;//今の時刻
    public CellSet(int rule,int n,int tmax,Location location){
        this.n=n;//x方向
        this.tmax=tmax;//z方向
        cell_list=new int[n];
        this.location=location;
        SetRule(rule);
        SetInitialState();
    }
    //ルールを設定する
    //rule[]に代入する
    private void SetRule(int rule){
        int rulenumber=rule;
        for(int i=0;i<r;i++){
            this.rule[i]=rulenumber%2;
            rulenumber=rulenumber/2;
        }
    }
    //初期状態をワールド上のブロックから設定する
    //Black_WOOLのときに1とする
    private void SetInitialState(){
        for(int i=0;i<n;i++){
            Location loc=location.clone();
            loc=loc.add((double)i,0.0,0.0);
            if(loc.getBlock().getType()== Material.BLACK_WOOL){
                cell_list[i]=1;
            }
            else{
                cell_list[i]=0;
                loc.getBlock().setType(Material.WHITE_WOOL);
            }
        }
        this.t=1;
    }
    public boolean SetNextState(){
        if(t<tmax){
            int[] next=new int[n];
            for(int i=0;i<n;i++){
                next[i]=rule[cell_list[(i + 1)%n]  + cell_list[i] * 2 + cell_list[(i - 1+n)%n]*4];
            }
            for(int i=0;i<n;i++){
                cell_list[i]=next[i];
                Location loc=location.clone();
                loc=loc.add((double)i,0.0,(double)t);
                if(cell_list[i]==1){
                    loc.getBlock().setType(Material.BLACK_WOOL);
                }
                else{
                    loc.getBlock().setType(Material.WHITE_WOOL);
                }
            }
            //System.out.println(cell_list);
            this.t++;
            return true;
        }
        else{
            return false;
        }

    }
    public int getT(){
        return this.t;
    }
}
