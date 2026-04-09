class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int N = sequence.length;
        long pSum = 0;
        long pSum2 = 0;
        long end = 0;
        for(int i = 0 ; i < N ; i++){
            int parsed = (i%2== 0? 1 : -1)*sequence[i];
            
            pSum = Math.max(parsed, pSum + parsed);
            pSum2 = Math.max(-parsed, pSum2 - parsed);
            end = Math.max(pSum,end);
            end = Math.max(pSum2,end);
        }
        
        

        return end;
    }
}