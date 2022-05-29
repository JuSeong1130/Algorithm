package programeers.level4;
import java.util.*;
//https://programmers.co.kr/learn/courses/30/lessons/64063?language=java
public class hotel_room_assignment {
    public static void main(String[] args) {
             long k=10;
             long room_number[] = {1,3,4,1,3,1};
             long[] res = solution(k,room_number);
        System.out.println(Arrays.toString(res));


    }
    //Union & Find 라는 알고리즘으로 푼 재귀적 풀이
    static Map<Long, Long> map = new HashMap<>();
    public static long[] solution(long k, long[] room_number) {
        int n = room_number.length;
        long[] answer = new long[n];

        for (int i = 0; i < n; i++) {
            answer[i] = findEmptyRoom(room_number[i]);
        }

        return answer;
    }

     public static long findEmptyRoom(long room) {
        if (!map.containsKey(room)) {
            map.put(room, room + 1);
            return room;
        }

        long nextRoom = map.get(room);
        long emptyRoom = findEmptyRoom(nextRoom);
        map.put(room, emptyRoom);
        return emptyRoom;
    }

    //map을 이용해 풀었지만 O(N)에 수렴하는건 같음 잘못품
    public static long[] solution11(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        Map<Long,Long> map = new HashMap<Long,Long>();

        for(int i=0;i<room_number.length;i++){
            if(map.containsKey(room_number[i])){ //키가 있다면 넣고 위아래 판별해서 위 제일 끝값 자기부터 밑에연결되어있는대까지
                answer[i]=map.get(room_number[i]); //이미 저장되어있으니 바로넣으면됨
                long upper_idx=map.get(room_number[i])+1;
                long value=0;
                //위로검사
                while(true){
                    if(map.containsKey(upper_idx)){
                        upper_idx++;
                    }else{//없다면 전 인덱스에 있는 value 값 저장하고 끝냄
                        value=map.getOrDefault(upper_idx-1,map.get(room_number[i])+1);
                        break;
                    }
                }
                map.put(map.get(room_number[i]),value);
                long lower_idx=map.get(room_number[i])-1;
                //밑으로 검사 갱신
                while(true){
                    if(map.containsKey(lower_idx)){
                        map.put(lower_idx,value);
                        lower_idx--;
                    }else{//없다면 전 인덱스에 있는 value 값 저장하고 끝냄
                        break;
                    }
                }
            }else{ //키가 없다면 넣고 위아래 판별해서 값이 들어왔을때 다음에 어디가면 되는지 설정
                //여기서 판별해서

                /* 2. 들어있지 않다면
                 *  answer에는 그값 그대로 들어가고
                 *  갱신을 해야하는데 밑에하고 위에하고 연결되있는거 파악해서
                 *  위 끝값 value 가져와서 자신부터 아래까지 갱신
                 *
                 * */
                answer[i]=room_number[i];

                long upper_idx=room_number[i]+1;
                long value=0;
                //위로검사
                while(true){
                    if(map.containsKey(upper_idx)){
                        upper_idx++;
                    }else{//없다면 전 인덱스에 있는 value 값 저장하고 끝냄
                        value=map.getOrDefault(upper_idx-1,room_number[i]+1);
                        break;
                    }
                }
                map.put(room_number[i],value);
                long lower_idx=room_number[i]-1;
                //밑으로 검사 갱신
                while(true){
                    if(map.containsKey(lower_idx)){
                        map.put(lower_idx,value);
                        lower_idx--;
                    }else{//없다면 전 인덱스에 있는 value 값 저장하고 끝냄
                        break;
                    }
                }
            }
        }
        return answer;
    }

    //1. map에 들어있다면
    //  이미 다음곳을 지정해 놓은 상태이니 answer[i]=map.get(room_number[i]); 값을 넣고
    // 사용된곳은 키에 없으니까 들어가는거니까 거기부터 위아래 판단해서 넣어주는거임
    //  다음곳도 포함해서 갱신해줘야함
    /*

    /* 2. 들어있지 않다면
     *  answer에는 그값 그대로 들어가고
     *  갱신을 해야하는데 밑에하고 위에하고 연결되있는거 파악해서
     *  위 끝값 value 가져와서 자신부터 아래까지 갱신
     *
     * */
    /*
     1. 들어있다면
            answer은 map에서 가져온 값 넣고
            그 방을 썻으니 값을 다시 갱신해줘야하는데
            위하고 밑에하고 연결되어있는것들 둘다 +1씩해서 넣어줌

     2. 들어있지않다면
        그 자리에 넣으면되는데
        밑에 위에 비교해서 다음 자리 map에넣기
    */
}
