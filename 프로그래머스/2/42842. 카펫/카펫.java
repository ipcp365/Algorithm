class Solution {
    public int[] solution(int brown, int yellow) {
        int total = brown + yellow;
        int S = (brown + 4) / 2;           // w + h
        int D = S * S - 4 * total;         // 판별식
        int r = (int) Math.sqrt(D);        // √D (항상 정수 제곱이 되도록 입력이 주어짐)

        int w = (S + r) / 2;
        int h = (S - r) / 2;
        return new int[]{w, h};
    }
}