/**
 * 1/28/2019
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.version;

import ir.aliloc.api.config.GlobalConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @RequestMapping(value = "/version", method = RequestMethod.GET)
    public ResponseEntity<String> getVersion() {
        return new ResponseEntity<String>(GlobalConstant.APP_VERSION, HttpStatus.OK);
    }
}
