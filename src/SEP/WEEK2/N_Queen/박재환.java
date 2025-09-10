package SEP.WEEK2.N_Queen;

import java.util.*;
import java.io.*;
public class 박재환 {
    static BufferedReader br;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        init();
        br.close();
    }

    static int  caseCnt;        // 나올 수 있는 경우의 수
    static int boardSize;       // 체스판의 크기 N
    static boolean[][] board;       // 체스판
    static void init() throws IOException {
        caseCnt = 0;
        boardSize = Integer.parseInt(br.readLine().trim());     // 최대 크기는 15
        board = new boolean[boardSize][boardSize];
        findSafeCase(0);
        System.out.println(caseCnt);
    }
    /*
        - 기본적으로 같은 행과 열에는 퀸을 둘 수 없다.
        - N 의 최대 값이 15 이므로, 완전 탐색을 해도 괜찮을 것 같다.
        - 즉 O(N!) 의 시간 복잡도를 가진다.
            - 가지치기를 하면 조금 더 줄어들 듯?
     */
    static void findSafeCase(int rowIdx) {
        if(rowIdx == boardSize) {       // 가장 마지막까지 탐색을 끝냈을 때
            caseCnt++;
            return;
        }

        // 탐색할 구역이 더 남아있는 경우
        for(int col=0; col<boardSize; col++) {
            if(!isSafe(rowIdx, col)) continue;   // 현재 구역에 둘 수 없음

            board[rowIdx][col] = true;  // 현 위치에 퀸을 두고 다음 구역 탐색
            findSafeCase(rowIdx+1);
            board[rowIdx][col] = false; // 백트래킹
        }
    }

    static int[] dx = {-1,-1,-1};
    static int[] dy = {0,1,-1};
    // 현 위치가 안전한지 확인
    static boolean isSafe(int x, int y) {
        for(int dir=0; dir<3; dir++) {  // 탑다운 형식으로 탐색하므로, 현재 위치에서 위쪽만 탐색하면 됨
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            while(isBoard(nx, ny)) {
                if(board[nx][ny]) return false;
                nx += dx[dir];
                ny += dy[dir];
            }
        }
        return true;
    }

    static boolean isBoard(int x, int y) {
        return !(x < 0 || y < 0 || x >= boardSize || y >= boardSize);
    }

    static boolean isSafe(int x, int y) {
        // 위쪽 방향 탐색
        for(int nx=x; nx > -1; nx--) {
            if(board[nx][y]) return false;
        }
        // 왼쪽 위 방향 탐색
        for(int nx=x, ny=y; nx > -1 && ny > -1; nx--, ny--) {
            if(board[nx][ny]) return false;
        }
        // 오른쪽 위 방향 탐색
        for(int nx=x, ny=y; nx > -1 && ny < boardSize; nx--, ny++) {
            if(board[nx][ny]) return false;
        }
        return true;
    }
}
