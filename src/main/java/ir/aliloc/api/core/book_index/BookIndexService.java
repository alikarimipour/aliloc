/**
 * 12/8/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.book_index;

import ir.aliloc.api.core.util.CustomMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
class BookIndexService implements IBookIndexService {

    @Autowired
    private IBookIndexDAO mIBookIndexDAO;
    @Autowired
    private CustomMapperService mCustomMapperService;

    @Override
    public List<BookIndexDTO> getBookIndexByBookId(long bookId) throws Exception {
        List<BookIndex> bookIndices = mIBookIndexDAO.getBookIndexByBookId(bookId);
        List<BookIndexDTO> bookIndexDTOList = mCustomMapperService.bookIndexListToBookIndexDTOList(bookIndices);
        List<BookIndexDTO> finalResult = new ArrayList<>();
        int index = 1;
        Stack<BookIndexDTO> stack = new Stack<>();
        if(bookIndexDTOList.size()!=0) {
            stack.push(bookIndexDTOList.get(0));
            finalResult.add(bookIndexDTOList.get(0));
            while (!stack.empty()) {
                if (index == bookIndexDTOList.size()) {
                    break;
                }
                BookIndexDTO peek = stack.peek();
                BookIndexDTO current = bookIndexDTOList.get(index++);
                if (current.getLevel() == 0) {
                    finalResult.add(current);
                }
                if (peek.getLevel() == current.getLevel()) {
                    BookIndexDTO a = stack.pop();
                    if (current.getLevel() == a.getLevel() && !stack.isEmpty()) {
                        peek = stack.peek();
                        List<BookIndexDTO> subTitles = peek.getSubTitles();
                        subTitles.add(current);
                        peek.setSubTitles(subTitles);
                    }
                    stack.push(current);
                } else if (peek.getLevel() < current.getLevel()) {
                    stack.push(current);
                    List<BookIndexDTO> subTitles = peek.getSubTitles();
                    subTitles.add(current);
                    peek.setSubTitles(subTitles);
                } else {
                    while (peek.getLevel() != current.getLevel()) {
                        peek = stack.pop();
                    }
                    stack.push(current);
                }
            }
        }
        return finalResult;
    }
}
