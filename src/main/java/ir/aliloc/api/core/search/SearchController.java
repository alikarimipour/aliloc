/**
 * 12/11/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.search;

import ir.aliloc.api.config.MessageConstant;
import ir.aliloc.api.exception.CustomizeResponseEntityExceptionHandler;
import ir.aliloc.api.core.models.init.MainModel;
import ir.aliloc.api.core.models.request.SearchBookRequest;
import ir.aliloc.api.core.models.response.SearchBookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ISearchService mISearchService;

    @RequestMapping(value = "/advance", method = RequestMethod.POST)
    public ResponseEntity<MainModel<List<SearchBookResponse>>> searchByFiler(@RequestBody SearchBookRequest searchBookRequest) {
        MainModel<List<SearchBookResponse>> mainModel = new MainModel<>();
        try {
            mainModel.setMessage(MessageConstant.MESSAGE_SUCCESS);
            mainModel.setStatusCode(HttpStatus.OK.value());
            mainModel.setResult(mISearchService.searchBook(searchBookRequest));
            return new ResponseEntity<>(mainModel, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new CustomizeResponseEntityExceptionHandler().handleAllExceptions(e);
        }
    }
}
