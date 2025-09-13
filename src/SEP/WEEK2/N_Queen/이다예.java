import java.util.Scanner;

public class Main {
    static int N;                      // 체스판 크기
    static int[][] queenAttack;         // 체스판
    static int result = 0;      // 정답

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        queenAttack = new int[N][N];

        // 첫 행의 각 열에서 퀸을 배치
        for (int col = 0; col < N; col++) {
            dfs(0, col, 1);             // 행, 열, 현재 깊이
        }

        System.out.println(result);
        sc.close();
    }

    // y: 행, x: 열, depth: 현재 퀸 개수
    static void dfs(int y, int x, int depth) {
        // 마지막 행까지 퀸을 모두 놓았다면 경우의 수 증가
        if (depth == N) {
            result++;
            return;
        }

        markAttack(y, x, +1);

        // 다음 행에서 공격 받지 않는 칸에 퀸 놓기
        for (int col = 0; col < N; col++) {
            if (queenAttack[y + 1][col] == 0) {
                queenAttack[y + 1][col] = 1;
                dfs(y + 1, col, depth + 1);
                queenAttack[y + 1][col] = 0;      // 다음 행을 위해 퀸 다시 초기화
            }
        }

        // 현재 퀸 공격 범위 제거
        markAttack(y, x, -1);
    }

    static void markAttack(int y, int x, int delta) {
        // 아래 방향
        for (int row = y + 1; row < N; row++) {
            queenAttack[row][x] += delta;
        }

        // 좌하단 대각선
        int col = x - 1;
        int row = y + 1;
        while (col >= 0 && row < N) {
            queenAttack[row++][col--] += delta;
        }

        // 우하단 대각선
        col = x + 1;
        row = y + 1;
        while (col < N && row < N) {
            queenAttack[row++][col++] += delta;
        }
    }
}
