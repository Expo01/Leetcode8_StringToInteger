class Solution {
    public int myAtoi(String input) {
        int sign = 1;
        int result = 0;
        int index = 0;
        int n = input.length();

        // Discard all spaces from the beginning of the input string.
        while (index < n && input.charAt(index) == ' ') {
            index++;
        }

        // sign = +1, if it's positive number, otherwise sign = -1.
        if (index < n && input.charAt(index) == '+') {
            sign = 1;
            index++;
        } else if (index < n && input.charAt(index) == '-') {
            sign = -1;
            index++;
        }

        // Traverse next digits of input and stop if it is not a digit
        while (index < n && Character.isDigit(input.charAt(index))) {
            int digit = input.charAt(index) - '0';

            // Check overflow and underflow conditions.
            if ((result > Integer.MAX_VALUE / 10) ||
                    (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
//                 If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                //return Integer.MAX_VALUE * sign; this doesn't work because last digitt in maxcx and min different
            }

            // Append current digit to the result.
            result = 10 * result + digit;
            index++;
        }

        // We have formed a valid number without any overflow/underflow.
        // Return it after multiplying it with its sign.
        return sign * result;
    }
}


/*
class Solution {
    public int myAtoi(String s) {
        StringBuilder ans = new StringBuilder();
        char[] sArray =  s.toCharArray();
        for(char c: sArray){
            if(c == '-'){
                ans.append(c);
            }
            if(Character.isDigit(c)){
                ans.append(c);
            }
        }
        return Integer.parseInt(ans.toString());
    }
} // this doesn't work because we must discard anything after a whitespace that is not a prefix whitespace

 */

// '   1234' => 1234 (whitespaces at beginning are removed)
//'      4' => 4    (whitespaces at beginning are removed)
//' 12   4' => 12   (only the leading whitespaces are removed)
// '12345 567 v' => 12345 (digits are appended until a non-digit character occurs)
//'00123'       => 00123 => 123 (0s in the beginning of the numbers are discarded)
// '-+12' => 0   (another sign after one sign is considered as non-digit character)
// '123 45 567 v' => 123 (we stopped when a space character occured)
//'a+123 bcd 45' => 0   (we stopped when 'a' character occured in the beginning)
//'12345' => 12345 (integer is in 32-bit range)
//'9999999999999' => 2^31-1 (integer overflow)
//'-999999999999' => -2^31   (integer underflow)
// We will denote the maximum 32-bit integer value 231−1 = 21474836472^{31} - 1 \space = \space 21474836472
//31
// −1 = 2147483647 with INT_MAX,
// '214748363' (less than INT_MAX / 10) + '0' = '2147483630' (less than INT_MAX)
//'214748363' (less than INT_MAX / 10) + '9' = '2147483639' (less than INT_MAX)
//'1' (less than INT_MAX / 10) + '9' = '19' (less than INT_MAX)
// Notice that cases 1 and 2 are similar for overflow and underflow. The only difference is case 3: for overflow,
// we can append any digit between 0 and 7, but for underflow, we can append any digit between 0 and 8.