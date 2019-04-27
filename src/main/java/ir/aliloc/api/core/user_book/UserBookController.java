/**
 * 12/29/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.user_book;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.book.BookDTO;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.BookIdRequest;
import ir.aliloc.api.core.models.request.OffsetSizeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/book/download")
public class UserBookController {

    @Autowired
    private IUserBookService mIUserBookService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<MainModel<String>> addBookDownloadByUser(@RequestBody BookIdRequest bookIdRequest) {
        MainModel<String> mainModel = new MainModel<>();
        try {
            mIUserBookService.addBookById(bookIdRequest.getBookId());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS_ADD);
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setResult(MessageConstant.MESSAGE_SUCCESS_ADD);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/history", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<BookDTO>>> getDownloadHistory(@RequestBody OffsetSizeRequest offsetSizeRequest) {
        MainModel<List<BookDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setResult(mIUserBookService.getBookListOfUser(offsetSizeRequest.getOffset(), offsetSizeRequest.getSize()));
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
