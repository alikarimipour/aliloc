/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.audio_book;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.book.BookDTO;
import ir.aliloc.api.core.models.init.MainModel;
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
public class AudioBookController {

    @Autowired
    private IAudioBookService mIAudioBookService;

    @RequestMapping(value = "audio/book/list", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<BookDTO>>> getAllAudioBook(@RequestBody OffsetSizeRequest offsetSizeRequest) {
        MainModel<List<BookDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mIAudioBookService.getAllAudioBook(offsetSizeRequest.getOffset(), offsetSizeRequest.getSize()));
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "api/audio/book/list", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<BookDTO>>> getAllAudioBookWhitPurchaseUser(@RequestBody OffsetSizeRequest offsetSizeRequest) {
        MainModel<List<BookDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setResult(mIAudioBookService.getAllAudioBookWhitPurchaseUser(offsetSizeRequest.getOffset(), offsetSizeRequest.getSize()));
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
