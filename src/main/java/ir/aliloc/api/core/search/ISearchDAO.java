/**
 * 12/11/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.search;

import ir.aliloc.api.core.enums.ESearchFilter;

import java.util.List;

interface ISearchDAO {


    List<Object[]> searchTitleByFilter(String text, ESearchFilter searchFilter, int offset, int size) throws Exception;

    List<Object[]> searchIndexByFilter(String text, ESearchFilter searchFilter, int offset, int size) throws Exception;

    List<Object[]> searchTextByFilter(String text, ESearchFilter searchFilter, int offset, int size) throws Exception;

}
