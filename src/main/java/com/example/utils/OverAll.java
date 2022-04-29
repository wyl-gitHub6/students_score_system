package com.example.utils;

import com.example.constant.MyConstant;
import com.example.dao.ClassesDao;
import com.example.dao.StudentDao;
import lombok.val;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author wyl
 */
@Component
public class OverAll {

    @Resource
    private ClassesDao classesDao;

    @Resource
    private StudentDao studentDao;

    /**
     * 按照院系、专业、年级、班级、生成学生学号
     *
     * @param classesId 类id
     * @return {@link String}
     */
    public String getStudentNum(int classesId) {

        /*班级ID为0代表未分班  学号为空*/
        if (classesId == 0){
            return null;
        }

        String maxCode = String.valueOf(studentDao.findMaxCode(classesId));
        if (MyConstant.NULL.equals(maxCode) || maxCode.isEmpty() || maxCode.equals(MyConstant.ONE_STR)) {
            Map<String, String> map = classesDao.findCode(classesId);
            String collegeCode = map.get("collegeCode");
            String professionalCode = map.get("professionalCode");
            String gradeName = map.get("gradeName").substring(2);
            String classesCode = map.get("classesCode");

            return gradeName + collegeCode + professionalCode + classesCode + MyConstant.STUDENT_DEFAULT_CODE;
        }
        return maxCode;
    }

    public Pair<String, BigDecimal> calculate(Object obj, double usualGrade, double testGrade){
        val list = (ArrayList<Map<String, Integer>>)obj;

        StringBuilder builder = new StringBuilder();
        BigDecimal value = BigDecimal.valueOf(0);

        if (list.size() == 0){
            //没阶段成绩 按照平时成绩的百分之30 期末成绩的百分之70
            BigDecimal scoreGrade = BigDecimal.valueOf(testGrade).multiply(BigDecimal.valueOf(0.7))
                    .add(BigDecimal.valueOf(usualGrade).multiply(BigDecimal.valueOf(0.3)))
                    .setScale(1, RoundingMode.HALF_UP);

            return ImmutablePair.of(builder.toString(), scoreGrade);
        }

        int i =0;
        for (Map<String, Integer> item : list) {
            i++;
            Integer stageGrade = item.get("value");
            //获取阶段总成绩
            value = value.add(BigDecimal.valueOf(stageGrade));
            builder.append("阶段").append(i).append(":").append(stageGrade).append(" ");
        }

        //计算总分 阶段平均成绩的百分之40 期末成绩的百分之40 平时成绩的百分之20
        BigDecimal scoreGrade = value.divide(BigDecimal.valueOf(i), 1, RoundingMode.HALF_UP)
                .add(BigDecimal.valueOf(testGrade))
                .multiply(BigDecimal.valueOf(0.4))
                .add(BigDecimal.valueOf(usualGrade).multiply(BigDecimal.valueOf(0.2)))
                .setScale(1, RoundingMode.HALF_UP);

        return ImmutablePair.of(builder.toString(),scoreGrade);
    }
}
