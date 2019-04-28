/**
 * 11/14/2018
 * author: alikarimipour157@gmail.com
 */
package ir.aliloc.api.core.multimedia;

interface IMultiMediaDAO {

    MultiMedia addMultimedia(MultiMedia multiMedia) throws Exception;

    MultiMedia getMultimediabyId(long id) throws Exception;
}
