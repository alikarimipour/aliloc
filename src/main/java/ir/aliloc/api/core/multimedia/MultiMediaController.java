/**
 * 12/2/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.multimedia;

import ir.aliloc.api.core.user_rate.IUserRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class MultiMediaController {

    @Autowired
    private IUserRateService rateService;

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://alilocMultimedia//";

  /*  @RequestMapping(value = "/multimedia/add", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<UserRateDTO>>> getHistoryRateOfUser(@RequestBody OffsetSizeRequest offsetSizeRequest) {
        MainModel<List<UserRateDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setResult(rateService.getHistoryRateOfUser(offsetSizeRequest));
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }*/


    @PostMapping("/multimedia/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }


        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }
}
