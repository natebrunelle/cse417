public class Oven{

    public static int[][] mem;
    public static boolean[][] choices;

    public static int oven_bottomup(int time, int[] times, int[] values){
        mem = new int[times.length][time+1];
        mem[0][0] = 0;
        for(int m = 0; m <= time; m++){ // fill in first row
            mem[0][m] = times[0] <= m ? values[0] : 0;
        }
        for(int item = 1; item < times.length; item++){
            mem[item][0] = 0; // fill in first column
            for(int m = 1; m <= time; m++){
                int exclude = mem[item-1][m]; // profit if we exclude this item
                mem[item][m] = exclude;
                if(times[item] <= m){ // if we have enough time for this item
                    int include = mem[item-1][m-times[item]]+values[item]; //profit if we include this item
                    if(include > exclude){ // keep the best choice
                        mem[item][m] = include;
                    }
                }
            }
        }
        return mem[times.length-1][time];
    }

    public static int oven_bottomup_lowmemory(int time, int[] times, int[] values){
        mem = new int[2][time+1];
        mem[0][0] = 0;
        for(int m = 0; m <= time; m++){ // fill in first row
            mem[0][m] = times[0] <= m ? values[0] : 0;
        }
        for(int item = 1; item < times.length; item++){
            mem[1][0] = 0; // fill in first column
            for(int m = 1; m <= time; m++){
                int exclude = mem[0][m]; // profit if we exclude this item
                mem[1][m] = exclude;
                if(times[item] <= m){ // if we have enough time for this item
                    int include = mem[0][m-times[item]]+values[item]; //profit if we include this item
                    if(include > exclude){ // keep the best choice
                        mem[1][m] = include;
                    }
                }
            }
            for(int m = 1; m <= time; m++){ // copy the "working row" to the "subproblem row"
                mem[0][m] = mem[1][m];
            }
        }
        return mem[0][time];
    }

    public static int oven_topdown(int time, int[] times, int[] values){
        mem = new int[times.length][time+1]; // initialize our memories
        for(int i = 0; i < mem.length; i++){
            for(int j = 0; j< mem[0].length; j++){
                mem[i][j]=-1;
            }
        }
        choices = new boolean[times.length][time+1];
        return oven(times.length-1, time, times, values);
    }

    private static int oven(int item, int time, int[] times, int[] values){
        if(item < 0){ // base case
            return 0;
        }
        if(mem[item][time]>=0){ // check if we've solved this problem before
            return mem[item][time]; 
        }
        int exclude = oven(item-1, time, times, values); // profit if we exclude this item
        int answer = exclude;
        if(times[item] <= time){ // if we have enough time for this item
            int include = oven(item-1, time-times[item], times, values) + values[item]; //profit if we include this item
            if(include > exclude){ // keep the best choice
                choices[item][time] = true;
                answer = include;
            }
        }
        mem[item][time] = answer;
        return answer;
    }


    public static void main(String[] args){
        int[] times = {3,4,2,7,3,3};
        int[] values = {3,4,4,7,2,1};
        System.out.println(oven_topdown(10, times, values));
        System.out.println(oven_bottomup(10, times, values));
        System.out.println(oven_bottomup_lowmemory(10, times, values));
        for(int i = 0; i < mem.length; i++){
            for(int j = 0; j< mem[0].length; j++){
                System.out.print(mem[i][j] + "\t");
            }
            System.out.println();
        }
    }
}