package ir.aliloc.api;

import com.google.gson.Gson;
import ir.aliloc.api.core.cours_category.CourseCategoryDTO;
import ir.aliloc.api.core.enums.ECourseTypeEnum;
import ir.aliloc.api.core.question.IQuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlilocApplicationTests {

	@Autowired
 	private IQuestionService mIQuestionService;

	@Test
	public void contextLoads() {
		// mICourseCategoryService.getListOfCourseCategoryByType(ECourseTypeEnum.COURSE);


		/*User user=new User();
		user.setId(1);
		List<CertificateDTO> certificates= mICertificateService.getCertificateHistory(user);
		System.out.println("salam");
		System.out.println("salam");
		System.out.println("salam");
		System.out.println(certificates);
		System.out.println(certificates);*/
		CourseCategoryDTO courseCategoryDTO=new CourseCategoryDTO();
		courseCategoryDTO.setType(ECourseTypeEnum.COURSE);
		Gson gson=new Gson();
		System.out.println(gson.toJson(courseCategoryDTO));

	}


}
