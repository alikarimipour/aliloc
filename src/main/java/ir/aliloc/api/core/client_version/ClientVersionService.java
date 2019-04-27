/**
 * 10/9/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.client_version;


import ir.aliloc.api.core.enums.EOS;
import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
class ClientVersionService implements IClientVersionService {

    @Autowired
    private IClientVersionDAO clientVersionDAO;
    @Autowired
    private CustomMapperService mCustomMapperService;

    @Override
    public ClientVersionDTO getClientVersion(EOS EOS, int versionCode) throws Exception {
        ClientVersion clientVersion = clientVersionDAO.getClientVersion(EOS,versionCode);
        String message = null;
        ClientVersionEnum clientVersionEnum = null;
        if (clientVersion.isForce()) {
            clientVersionEnum = ClientVersionEnum.FORCE;
        } else {
            if (clientVersion.getMaxVersionCode() == versionCode) {
                clientVersionEnum = ClientVersionEnum.LATEST;
            } else if (versionCode > clientVersion.getMinVersionCode() && versionCode < clientVersion.getMaxVersionCode()) {
                clientVersionEnum = ClientVersionEnum.UPDATE;
            }
        }
        return mCustomMapperService.clientVersionToClientVersionDTO(message, clientVersion.getUpdateLink(), clientVersionEnum,clientVersion.getLastVersionName());
    }
}
