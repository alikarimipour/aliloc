/**
 * 11/14/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.multimedia;

interface IMultiMediaDAO {

    MultiMedia addProfile(MultiMedia multiMedia) throws Exception;

    MultiMedia getMultiMediaByAddressId(long multimediaAddressId) throws Exception;
}
