package com.freddy.kulachat.manager;

import com.freddy.kulachat.R;
import com.freddy.kulachat.entity.Expression;

import java.util.ArrayList;
import java.util.List;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/04 14:40
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class ExpressionManager {

    private static volatile ExpressionManager instance;
    private List<Expression> mNormalExpressionList;
    private static final int NORMAL_COUNT_BY_ROW = 7;

    private ExpressionManager() {
        init();
    }

    public static ExpressionManager getInstance() {
        if (instance == null) {
            synchronized (ExpressionManager.class) {
                if (instance == null) {
                    instance = new ExpressionManager();
                }
            }
        }

        return instance;
    }

    private void init() {
        initExpressionData();
    }

    private void initExpressionData() {
        mNormalExpressionList = new ArrayList<>();
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_1, "$ne#1^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_2, "$ne#2^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_3, "$ne#3^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_4, "$ne#4^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_5, "$ne#5^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_6, "$ne#6^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_7, "$ne#7^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_8, "$ne#8^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_9, "$ne#9^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_10, "$ne#10^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_11, "$ne#11^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_12, "$ne#12^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_13, "$ne#13^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_14, "$ne#14^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_15, "$ne#15^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_16, "$ne#16^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_17, "$ne#17^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_18, "$ne#18^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_19, "$ne#19^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_20, "$ne#20^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_21, "$ne#21^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_22, "$ne#22^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_23, "$ne#23^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_24, "$ne#24^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_25, "$ne#25^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_26, "$ne#26^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_27, "$ne#27^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_28, "$ne#28^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_29, "$ne#29^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_30, "$ne#30^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_31, "$ne#31^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_32, "$ne#32^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_33, "$ne#33^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_34, "$ne#34^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_35, "$ne#35^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_36, "$ne#36^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_37, "$ne#37^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_38, "$ne#38^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_39, "$ne#39^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_40, "$ne#40^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_41, "$ne#41^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_42, "$ne#42^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_43, "$ne#43^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_44, "$ne#44^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_45, "$ne#45^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_46, "$ne#46^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_47, "$ne#47^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_48, "$ne#48^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_49, "$ne#49^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_50, "$ne#50^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_51, "$ne#51^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_52, "$ne#52^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_53, "$ne#53^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_54, "$ne#54^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_55, "$ne#55^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_56, "$ne#56^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_57, "$ne#57^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_58, "$ne#58^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_59, "$ne#59^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_60, "$ne#60^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_61, "$ne#61^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_62, "$ne#62^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_63, "$ne#63^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_64, "$ne#64^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_65, "$ne#65^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_66, "$ne#66^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_67, "$ne#67^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_68, "$ne#68^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_69, "$ne#69^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_70, "$ne#70^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_71, "$ne#71^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_72, "$ne#72^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_73, "$ne#73^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_74, "$ne#74^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_75, "$ne#75^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_76, "$ne#76^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_77, "$ne#77^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_78, "$ne#78^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_79, "$ne#79^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_80, "$ne#80^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_81, "$ne#81^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_82, "$ne#82^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_83, "$ne#83^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_84, "$ne#84^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_85, "$ne#85^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_86, "$ne#86^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_87, "$ne#87^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_88, "$ne#88^"));
        mNormalExpressionList.add(new Expression(R.drawable.img_expression_89, "$ne#89^"));

        int emptyCount = NORMAL_COUNT_BY_ROW + NORMAL_COUNT_BY_ROW - mNormalExpressionList.size() % NORMAL_COUNT_BY_ROW;
        for(int i = 0; i < emptyCount; i++) {
            mNormalExpressionList.add(new Expression(0, null));
        }
    }

    public List<Expression> getNormalExpressionList() {
        return mNormalExpressionList;
    }
}
