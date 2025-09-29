class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        String[] video_t = video_len.split(":");
        String[] pos_t = pos.split(":");
        String[] op_start_t = op_start.split(":");
        String[] op_end_t = op_end.split(":");
        int video_time = Integer.parseInt(video_t[0])*60 + Integer.parseInt(video_t[1]);
        int pos_time = Integer.parseInt(pos_t[0])*60 + Integer.parseInt(pos_t[1]);
        int op_start_time = Integer.parseInt(op_start_t[0])*60 + Integer.parseInt(op_start_t[1]);
        int op_end_time = Integer.parseInt(op_end_t[0])*60 + Integer.parseInt(op_end_t[1]);

        if(pos_time>=op_start_time && pos_time<=op_end_time) pos_time=op_end_time;

        for(String command : commands){
            if(command.equals("next")) pos_time+=10;
            else pos_time-=10;

            if(pos_time<0) pos_time=0;
            else if(pos_time>video_time) pos_time=video_time;
            if(pos_time>=op_start_time && pos_time<=op_end_time) pos_time=op_end_time;
        }
        String mm = Integer.toString(pos_time/60);
        String ss = Integer.toString(pos_time%60);
        if(mm.length()==1) mm = "0"+mm;
        if(ss.length()==1) ss = "0"+ss;
        return mm+":"+ss;
    }
}