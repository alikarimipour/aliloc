/**
 * 12/15/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.purchase_audio;

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
@RequestMapping("api/purchase")
public class PurchaseController {

    @Autowired
    private IPurchaseAudioService mIPurchaseAudioService;

    @RequestMapping(value = "audio/history", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<BookDTO>>> getHistoryPurchase(@RequestBody OffsetSizeRequest offsetSizeRequest) {
        MainModel<List<BookDTO>> mainModel = new MainModel<>();
        try {
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setResult(mIPurchaseAudioService.getBookAudioListOfUser(offsetSizeRequest.getOffset(), offsetSizeRequest.getSize()));
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }

    @RequestMapping(value = "/audio", method = RequestMethod.POST)
    public ResponseEntity<MainModel<BookDTO>> getAudioListOfBook(@RequestBody BookIdRequest bookIdRequest) {
        MainModel<BookDTO> mainModel = new MainModel<>();

        try {
            mainModel.setResult(mIPurchaseAudioService.getAudioBookPurchaseByBookId(bookIdRequest.getBookId()));
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }

    }
}
