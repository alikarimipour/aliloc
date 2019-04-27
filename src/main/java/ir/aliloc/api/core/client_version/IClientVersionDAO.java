/**
 * 10/9/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.client_version;

import ir.aliloc.api.core.enums.EOS;

interface IClientVersionDAO {

    ClientVersion getClientVersion(EOS EOS, int versionCode) throws Exception;
}
