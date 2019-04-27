/**
 * 12/11/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.search;


import ir.aliloc.api.core.enums.ESearchFilter;
import ir.aliloc.api.core.util.GlobalService;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class SearchDAO implements ISearchDAO {

    @Autowired
    private SessionFactory mSessionFactory;
    @Autowired
    private GlobalService mGlobalService;

    @Override
    public List<Object[]> searchTitleByFilter(String text, ESearchFilter searchFilter, int offset, int size) throws Exception {
        StringBuilder queryString = new StringBuilder("select b.id ,b.description,b.pageCount,b.price,b.printDate,b.printOrder,b.title, " +
                "m.id as tId,m.description as tDescription,m.duration as tDuration,m.mime as tMime,m.quality as tQuality,m.size as tSize,m.url as tUrl ," +
                "ba.id as audioId,ba.price as audioPrice,ba.title as audioTitle,ma.id as demoId,ma.description as demoDescription,ma.duration as demoDuration,ma.mime as demoMime, ma.quality as demoQuality,ma.size as demoSize,ma.url as demoUrl " +
                "from " +
                "book as b " +
                "left join multimedia as m on m.id = b.thumbnail_multimedia_id " +
                "left join audio_book as ba on ba.book_id = b.id " +
                "left join multimedia as ma on ma.id = ba.demo_id " +
                "where b.title like ");
        if (searchFilter == ESearchFilter.ALL) {
            queryString.append("'").append(text).append("'");
        } else {
            queryString = queryGenerator(queryString, text, searchFilter);
        }
        SQLQuery query = mSessionFactory.getCurrentSession().createSQLQuery(String.valueOf(queryString));
        query.setFirstResult(offset).setMaxResults(size);
        return query.list();
    }

    @Override
    public List<Object[]> searchIndexByFilter(String text, ESearchFilter searchFilter, int offset, int size) throws Exception {
        StringBuilder queryString = new StringBuilder("select " +
                "b.id ,b.description,b.pageCount,b.price,b.printDate,b.printOrder,b.title," +
                " m.id as tId,m.description as tDescription,m.duration as tDuration,m.mime as tMime,m.quality as tQuality,m.size as tSize,m.url as tUrl ," +
                " ba.id as audioId,ba.price as audioPrice,ba.title as audioTitle,ma.id as demoId,ma.description as demoDescription,ma.duration as demoDuration,ma.mime as demoMime, ma.quality as demoQuality,ma.size as demoSize,ma.url as demoUrl ," +
                " i.id as iId,i.title as iTitle,i.page as iPage,i.level as iLevel " +
                "from bookIndex as i " +
                "left join book as b on b.id = i.book_id " +
                "left join multimedia as m on m.id = b.thumbnail_multimedia_id " +
                "left join audio_book as ba on ba.book_id = b.id " +
                "left join multimedia as ma on ma.id = ba.demo_id " +
                "where i.title like ");
        SQLQuery query = mSessionFactory.getCurrentSession().createSQLQuery(String.valueOf(queryGenerator(queryString, text, searchFilter)));
        query.setFirstResult(offset).setMaxResults(size);
        return query.list();
    }

    @Override
    public List<Object[]> searchTextByFilter(String text, ESearchFilter searchFilter, int offset, int size) throws Exception {
        StringBuilder queryString = new StringBuilder("select b.id,b.description,b.pageCount,b.price,b.printDate,b.printOrder,b.title,\n" +
                " m.id as tId,m.description as tDescription,m.duration as tDuration,m.mime as tMime,m.quality as tQuality,m.size as tSize,m.url as tUrl ,\n" +
                "        ba.id as audioId,ba.price as audioPrice,ba.title as audioTitle,\n" +
                "        ma.id as demoId,ma.description as demoDescription,ma.duration as demoDuration,ma.mime as demoMime, ma.quality as demoQuality,ma.size as demoSize,ma.url as demoUrl ,\n" +
                "        p.id as pId,p.page as pPage,p.pageNumber as pNumber,p.foot as pFoot \n" +
                "        from bookPage as p \n" +
                "        left join book as b on b.id = p.book_id \n" +
                "        left join multimedia as m on m.id = b.thumbnail_multimedia_id \n" +
                "        left join audio_book as ba on ba.book_id = b.id \n" +
                "        left join multimedia as ma on ma.id = ba.demo_id where p.page like");
        queryString = queryGenerator(queryString, text, searchFilter);
        queryString.append(" or p.foot like ");
        String finalQuery = queryGenerator(queryString, text, searchFilter).toString();
        SQLQuery query = mSessionFactory.getCurrentSession().createSQLQuery(finalQuery);
        query.setFirstResult(offset).setMaxResults(size);
        return query.list();
    }


    private StringBuilder queryGenerator(StringBuilder queryString, String text, ESearchFilter searchFilter) {
        String[] words = mGlobalService.splitString(text);
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            queryString.append("'%").append(word).append("%'");
            if ((searchFilter == ESearchFilter.OR || searchFilter == ESearchFilter.ALL) && i != words.length - 1) {
                queryString.append("OR");
            } else if ((searchFilter == ESearchFilter.AND || searchFilter == ESearchFilter.ALL) && i != words.length - 1) {
                queryString.append("AND");
            }
        }
        return queryString;
    }
}
