/**
 * 12/3/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.BookIdRequest;
import ir.aliloc.api.core.models.request.OffsetSizeRequest;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private IBookService mIBookService;

    @RequestMapping(value = "book/list", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<BookDTO>>> getBookInfoList(@RequestBody OffsetSizeRequest offsetSizeRequest) {
        MainModel<List<BookDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setResult(mIBookService.getBookInfoList(offsetSizeRequest));
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "api/book/list", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<BookDTO>>> getBookList(@RequestBody OffsetSizeRequest offsetSizeRequest) {
        MainModel<List<BookDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mIBookService.getLoginBookList(offsetSizeRequest));
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/book/info", method = RequestMethod.POST)
    public ResponseEntity<MainModel<BookDTO>> getBookById(@RequestBody BookIdRequest bookIdRequest) {
        MainModel<BookDTO> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mIBookService.getBookById(bookIdRequest.getBookId()));
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

}
