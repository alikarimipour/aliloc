/**
 * 12/17/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.cours_category;

import ir.aliloc.api.security.IAuthenticationFaced;
import ir.aliloc.api.core.course.ICourseService;
import ir.aliloc.api.core.enums.ECourseTypeEnum;
import ir.aliloc.api.core.util.CustomMapperService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Transactional
class CourseCategoryService implements ICourseCategoryService {

    @Autowired
    private ICourseCategoryDAO mICourseCategoryDAO;
    @Autowired
    private ModelMapper mModelMapper;
    @Autowired
    private CustomMapperService mCustomMapperService;
    @Autowired
    private ICourseService mICourseService;
    @Autowired
    private IAuthenticationFaced mIAuthenticationFaced;

    @Override
    public List<CourseCategoryDTO> getListOfCourseCategory() throws Exception {
        List<CourseCategory> courseCategories = mICourseCategoryDAO.getAllCourseCategory();
        Type courseCategoryListType = new TypeToken<List<CourseCategoryDTO>>() {
        }.getType();
        List<CourseCategoryDTO> courseCategoryDTOList = mModelMapper.map(courseCategories, courseCategoryListType);
        for (CourseCategoryDTO courseCategoryDTO : courseCategoryDTOList) {
            mICourseService.checkActiveUserCourse(courseCategoryDTO.getCourseList());
        }
        return courseCategoryDTOList;
    }

    @Override
    public List<CourseCategoryDTO> getListOfCourseCategoryByType(ECourseTypeEnum courseTypeEnum) throws Exception {
        List<CourseCategory> courseCategories = mICourseCategoryDAO.getAllCourseCategoryByType(courseTypeEnum);
        List<CourseCategoryDTO> courseCategoryDTOList = mCustomMapperService.courseCategoryListToCourseCategoryDTOList(courseCategories);
        String username = mIAuthenticationFaced.getAuthentication().getName();
        if (!username.equals("anonymousUser")) {
            for (CourseCategoryDTO courseCategoryDTO : courseCategoryDTOList) {
                mICourseService.checkActiveUserCourse(courseCategoryDTO.getCourseList());
            }
        }
        return courseCategoryDTOList;
    }
}
