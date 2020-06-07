package com.zero.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * Created by zero on 2020/6/5.
 * desc:完整版的逆波兰计算器
 * 1) 支持 加减乘除括号
 * 2) 多位数，支持小数,
 * 3) 兼容处理,过滤任何空白字符，包括空格、制表符、换页符
 */
public class ReversePolishMultiCalc {

    // 匹配 +-*/() 运算符
    static final String SYMBOL = "\\+|-|\\*|/|\\(|\\)";
    static final String LEFT = "(";
    static final String RIGHT = ")";
    static final String ADD = "+";
    static final String MINUS = "-";
    static final String TIMES = "*";
    static final String DIVISION = "/";
    /**
     * 加減 + - 优先级为1
     */
    static final int LEVEL_01 = 1;
    /**
     * 乘除 * / 优先级为2
     */
    static final int LEVEL_02 = 2;

    /**
     * 括号 优先级无限大
     */
    static final int LEVEL_HIGH = Integer.MAX_VALUE;

    //符号栈和数据栈
    static Stack<String> stack = new Stack<>();
    static List<String> data = Collections.synchronizedList(new ArrayList<String>());

    /*
     * @author  李泽
     * @date  2020/6/5 9:21 AM
     * @desc 正则表达式去除所有空白符
     */
    public static String replaceAllBlank(String s) {
        // \\s+ 匹配任何空白字符，包括空格、制表符、换页符等等, 等价于[ \f\n\r\t\v]
        return s.replaceAll("\\s+", "");
    }

    /*
     * @author  李泽
     * @date  2020/6/5 9:31 AM
     * @desc 判断是不是数字 int double long float
     */
    public static boolean isNumber(String s) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(s).matches();
        //return true;
    }

    /*
     * @author  李泽
     * @date  2020/6/5 10:08 AM
     * @desc  判断是不是运算符
     */
    public static boolean isSymbol(String s) {
        return s.matches(SYMBOL);
    }

    /*
     * @author  李泽
     * @date  2020/6/5 10:09 AM
     * @desc 匹配运算等级
     */
    public static int calcLevel(String s) {
        if ("+".equals(s) || "-".equals(s)) {
            return LEVEL_01;
        } else if ("*".equals(s) || "/".equals(s)) {
            return LEVEL_02;
        }
        return LEVEL_HIGH;
    }

    /*
     * @author  李泽
     * @date  2020/6/5 10:48 AM
     * @desc 转换为逆波兰表达式，匹配
     */
    public static List<String> doMatch(String s) throws Exception {
        //判断null和空串
        if (s == null || "".equals(s.trim())) {
            throw new RuntimeException("empty");
        }
        //判断必须是数字
        if (!isNumber(s.charAt(0) + "")) {
            throw new RuntimeException("data illeagle,start not with a number:"+s.charAt(0));
        }
        //去空格
        s = replaceAllBlank(s);
        String each;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isSymbol(s.charAt(i) + "")) {
                each = s.charAt(i) + "";
                //栈为空，(操作符，或者 操作符优先级大于栈顶优先级 && 操作符优先级不是( )的优先级 及 是 ) 不能直接入栈
                if (stack.isEmpty() || LEFT.equals(each) ||
                        calcLevel(each) > calcLevel(stack.peek()) && calcLevel(each) < LEVEL_HIGH) {
                    stack.push(each);
                } else if (!stack.isEmpty() && calcLevel(each) <= calcLevel(stack.peek())) {
                    //栈非空，操作符优先级小于等于栈顶优先级时出栈入列，直到栈为空，或者遇到了(，最后操作符入栈
                    while (!stack.isEmpty() && calcLevel(each) <= calcLevel(stack.peek())) {
                        //遇到括号就停
                        if (calcLevel(stack.peek()) == LEVEL_HIGH) {
                            break;
                        }
                        data.add(stack.pop());
                    }
                    stack.push(each);
                } else if (RIGHT.equals(each)) {
                    // ) 操作符，依次出栈入列直到空栈或者遇到了第一个)操作符，此时)出栈
                    while (!stack.isEmpty() && LEVEL_HIGH >= calcLevel(stack.peek())) {
                        if (LEVEL_HIGH == calcLevel(stack.peek())) {
                            stack.pop();
                            break;
                        }
                        data.add(stack.pop());
                    }
                }
                start = i;//前一个运算符的位置
            } else if (i == s.length() - 1 || isSymbol(s.charAt(i + 1) + "")) { //非运算符
                each = start == 0 ? s.substring(start, i + 1) : s.substring(start + 1, i + 1);
                if (isNumber(each)) {
                    data.add(each);
                    continue;
                }
                throw new RuntimeException("data not match number");
            }
        }
        //如果栈里还有元素，此时元素需要依次出栈入列，可以想象栈里剩下栈顶为/，
        // 栈底为+，应该依次出栈 入列，可以直接翻转整个 stack 添加到队列
        Collections.reverse(stack);
        data.addAll(new ArrayList<>(stack));
        System.out.println(data);
        return data;
    }

    /*
     * @author  李泽
     * @date  2020/6/7 4:06 PM
     * @desc 算出结果
     */
    public static Double doCalc(List<String> list) {
        Double d = 0d;
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (list.size() == 1) {
            System.out.println(list);
            d = Double.valueOf(list.get(0));
            return d;
        }
        List<String> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list1.add(list.get(i));
            if (isSymbol(list.get(i))) {
                Double d1 = doTheMath(list.get(i - 2), list.get(i - 1), list.get(i));
                list1.remove(i);
                list1.remove(i - 1);
                list1.set(i - 2, d1+"");//不能使用tostring，会报空指针
                list1.addAll(list.subList(i + 1, list.size()));
                break;
            }
        }
        doCalc(list1);
        return d;
    }

    /*
     * @author  李泽
     * @date  2020/6/7 4:11 PM
     * @desc 运算
     */
    private static Double doTheMath(String s1, String s2, String symbol) {
        Double result = 0d;
        switch (symbol) {
            case ADD:
                result = Double.valueOf(s1) + Double.valueOf(s2);
                break;
            case MINUS:
                result = Double.valueOf(s1) - Double.valueOf(s2);
                break;
            case TIMES:
                result = Double.valueOf(s1) * Double.valueOf(s2);
                break;
            case DIVISION:
                result = Double.valueOf(s1) / Double.valueOf(s2);
                break;
            default:
                result = null;

        }
        return result;
    }

    public static void main(String[] args) {
        //String math = "9+(3-1)*3+10/2";
        String math = "12.8 + (2 - 3.55)*4+10/5.0";
        try {
            doCalc(doMatch(math));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
