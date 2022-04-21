package com.example.utils;

import com.example.constant.MyConstant;
import com.example.dao.ClassesDao;
import com.example.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wyl
 */
@Component
public class OverAll {

    @Autowired
    private ClassesDao classesDao;

    @Autowired
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
}
