package blackpig.com.calculatordemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btn_0;
    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private Button btn_5;
    private Button btn_6;
    private Button btn_7;
    private Button btn_8;
    private Button btn_9;
    private Button btn_point;
    private Button btn_plus;
    private Button btn_minus;
    private Button btn_mutiply;
    private Button btn_divide;
    private Button btn_equal;
    private Button btn_clear;
    private Button btn_del;
    private EditText et_input;
    private boolean needclear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0 = (Button) findViewById(R.id.btn_0);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_6 = (Button) findViewById(R.id.btn_6);
        btn_7 = (Button) findViewById(R.id.btn_7);
        btn_8 = (Button) findViewById(R.id.btn_8);
        btn_9 = (Button) findViewById(R.id.btn_9);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_minus = (Button) findViewById(R.id.btn_minus);
        btn_mutiply = (Button) findViewById(R.id.btn_mutiply);
        btn_divide = (Button) findViewById(R.id.btn_divide);
        btn_point = (Button) findViewById(R.id.btn_point);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_del = (Button) findViewById(R.id.btn_del);
        et_input = (EditText) findViewById(R.id.et_input);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_mutiply.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_del.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String str = et_input.getText().toString();
        switch (view.getId()) {
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if (needclear) {
                    str = "";
                    et_input.setText("");
                }
                et_input.setText(str + ((Button) view).getText());
                break;

            case R.id.btn_plus:
            case R.id.btn_minus:
            case R.id.btn_mutiply:
            case R.id.btn_divide:
                if (needclear) {
                    et_input.setText("");
                }
                et_input.setText(str + " " + ((Button) view).getText() + " ");
                break;
            case R.id.btn_clear:
                et_input.setText("");
                break;
            case R.id.btn_del:
                if (str != null && !str.equals("")) {
                    et_input.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.btn_equal:
                getResult();
                break;
        }
    }

    private void getResult() {
        needclear = true;
        String exp = et_input.getText().toString();
        double r = 0;
        int space = exp.indexOf(" ");// 用于搜索空格位置
        String s1 = exp.substring(0, space);//s1用于保存第一个运算数
        String op = exp.substring(space + 1, space + 2);//op用于保存运算符
        String s2 = exp.substring(space + 3);//s2用于保存第二个运算数
        double arg1 = Double.parseDouble(s1);//将运算数从string转换为Single
        double arg2 = Double.parseDouble(s2);
        if (op.equals("+")) {
            r = arg1 + arg2;
        } else if (op.equals("-")) {
            r = arg1 - arg2;
        } else if (op.equals("×")) {
            r = arg1 * arg2;
        } else if (op.equals("÷")) {
            if (arg2 == 0) {
                r = 0;
            } else {
                r = arg1 / arg2;
            }
        }
        if (!s1.contains(".") && !s2.contains(".")) {
            int result = (int) r;
            et_input.setText(result + "");
//            needclear = false;
        } else {
            et_input.setText(r + "");
//            needclear = false;
        }

    }
}
