
package javaapplication2;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class JavaApplication2 {

    
    public static ArrayList<Tree> sotr(ArrayList<Tree> tree){
        boolean flag;
        do{
            flag=true;
            for (int i=0;i<tree.size()-1;i++)
                if (tree.get(i).value>tree.get(i+1).value){
                    Tree x = tree.get(i);
                    tree.set(i, tree.get(i+1));
                    tree.set(i+1, x);
                    flag=false;
                }
        }
        while (!flag);
        return tree;
    }
    
    public static class Tree{
        public Tree left;
        public Tree right;
        public String lit;
        public int value;
        private Tree(int value, String lit) {
            this.lit=lit;
            this.value=value;
        }
    }
    public static ArrayList<String> ar = new ArrayList<>();
    public static ArrayList<String> codes = new ArrayList<>();
    public static String ans;
    public static void poiskElementa(Tree root,String el,String answer){
        if (root==null)
            return;
        if (root.lit.equals(el)){
            ans=answer;
            return;
        }          
        answer+="0";
        poiskElementa(root.left,el,answer);
        answer=answer.substring(0,answer.length()-1);
        answer+="1";
        poiskElementa(root.right,el,answer);
        answer=answer.substring(0,answer.length()-1);
    }
    public static Tree tree = new Tree(0,"");
    public static void codd() throws Exception{
        Scanner scanner = new Scanner(new File("input.txt"));
        FileWriter fw = new FileWriter(new File("codd.txt"));
        
        ArrayList<Integer> ari = new ArrayList<>();
        boolean was;
        while (scanner.hasNext()){
            String str= scanner.next();
            int curi=0;
            for (int i=0;i<str.length();i++){
                was=false;
                for (int j=0;j<ar.size();j++)
                    if (ar.get(j).equals(String.valueOf(str.charAt(i)))){
                        was=true;
                        curi=j;
                        break;
                    }
                if (!was){
                    ar.add(String.valueOf(str.charAt(i)));
                    ari.add(1);
                }
                else{
                    ari.set(curi, ari.get(curi)+1);
                }
                
            }
        }
        ArrayList<Tree> art = new ArrayList<>();
        for (int i=0;i<ar.size();i++)
            art.add(new Tree(ari.get(i),ar.get(i)));
        while (art.size()>1){
            art=sotr(art);
            tree=new Tree(0,"");
            tree.left=art.get(0);
            tree.right=art.get(1);
            art.remove(0);
            art.remove(0);
            tree.value=tree.left.value+tree.right.value;
            art.add(tree);
        }
        tree=art.get(0);
        
        for (int i=0;i<ar.size();i++){
            ans="";
            poiskElementa(tree,ar.get(i),"");
            codes.add(ans);
        }
        scanner.close();
        scanner = new Scanner(new File("input.txt"));
        while (scanner.hasNext()){
            String str= scanner.next();
            int curi=0;
            for (int i=0;i<str.length();i++){
                for (int j=0;j<codes.size();j++)
                    if (ar.get(j).equals(String.valueOf(str.charAt(i)))){
                        fw.write(codes.get(j)+" ");
                        break;
                    }
            }
        }
        scanner.close();
        fw.close();
    }
    
    public static void decodd() throws Exception{
        Scanner scanner = new Scanner(new File("codd.txt"));
        FileWriter fw = new FileWriter(new File("decodd.txt"));
         while (scanner.hasNext()){
            String str= scanner.next();
            String cod[]=str.split(" ");
            for (int i=0;i<cod.length;i++){
                for (int j=0;j<codes.size();j++)
                    if (codes.get(j).equals(cod[i])){
                        fw.write(ar.get(j));
                        break;
                    }
            }
        }
         scanner.close();
         fw.close();
    }
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        codd();
        decodd();
    }
    
}
