/**
 * 575
 * Given an expression s contains numbers, letters and brackets.
 * Number represents the number of repetitions inside the brackets(can be a string or another expression)．Please expand expression to be a string.
 *
 * eg:
 * Input: S = abc3[a]
 * Output: "abcaaa"
 */
public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public static String expressionExpand(String s) {
        // write your code here
        if (!s.contains("[")) {
            return s;
        }
        String target = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            int n = i;
            //查找第一个数字的位置 且拿到整个数字
            char c = s.charAt(i);
            target = String.valueOf(c);
            if(c>'0'&&c<'9' || c == '0'){
                if (c == '0') {
                    target = "0";
                }
                for (int j = i - 1; j > 0; j--) {
                    char d = s.charAt(j);
                    if (d>'0'&&d<'9') {
                        target = target.concat(String.valueOf(d));
                        n = j;
                    } else if ("[".equals(String.valueOf(d))){
                        break;
                    }else {
                        if (c == '0') {
                            if (d == '0') {
                                target = target.concat(String.valueOf(d));
                                n=j;
                                continue;
                            } else {
                                //去除这个
                                if (i -j == 1) {
                                    //获取下一个】
                                    int o = s.indexOf("]",i);
                                    String substring = s.substring(i, o + 1);
                                    //去掉这个字符串
                                    s = s.replace(substring, "");
                                    return expressionExpand(s);
                                }
                            }
                        }
                        break;
                    }
                }
                //if (flag) {
                    //颠倒
                    StringBuilder builder = new StringBuilder(target);
                    target = builder.reverse().toString();
                //}

                //获取【】查从该坐标起第一个】
                int first = s.indexOf("]", i);
                target = target + s.substring(i + 1, first + 1);
                //target = target + ss;

                String front = s.substring(0, n);
                String end = s.substring(first + 1);
                //生成下一个字符串
                target = front + getStr(target) + end;
                System.out.println(target);
                break;
            }/*else if (c=='0'){
                //看前面是不是字母 是字母去掉
                int indexx = i -1;
                char c1 = s.charAt(indexx);
                if (!(c1>'0'&&c1<'9')) {
                    s = s.substring(0, i -1);
                    expressionExpand(s);
                }
            }*/
        }


        return expressionExpand(target);
    }

    public static String getStr(String str) {
        int index = str.indexOf("[");
        String s1 = str.substring(0, index);
        StringBuilder sb = new StringBuilder("");
        String s2 = str.substring(index + 1, str.length() - 1);
        int number = Integer.parseInt(s1);
        for (int i = 0; i < number; i++) {
            sb.append(s2);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = expressionExpand("1[lintcode]4[abcAhj]4[wer]0[peer]");
        //System.out.println(s);
    }
}