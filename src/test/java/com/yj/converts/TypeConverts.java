package com.yj.converts;


import com.baomidou.mybatisplus.generator.config.converts.select.BranchBuilder;
import com.baomidou.mybatisplus.generator.config.converts.select.Selector;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * @author 邹敦宇
 * @version 1.0
 * @date 2020/11/13 15:07
 */
public class TypeConverts {
    public TypeConverts() {
    }

    static Selector<String, IColumnType> use(String param) {
        return new Selector<>(param.toLowerCase());
    }

    static BranchBuilder<String, IColumnType> contains(CharSequence value) {
        return BranchBuilder.of((s) -> s.contains(value));
    }

    static BranchBuilder<String, IColumnType> containsAny(CharSequence... values) {
        return BranchBuilder.of((s) -> {
            for (CharSequence value : values) {
                if (s.contains(value)) {
                    return true;
                }
            }

            return false;
        });
    }
}

