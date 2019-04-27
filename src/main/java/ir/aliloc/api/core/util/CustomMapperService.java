/**
 * 10/6/2018
 * author: mostafa.mashayekhi9@gmail.com
 */
package ir.aliloc.api.core.util;


import ir.aliloc.api.core.audio_book.AudioBook;
import ir.aliloc.api.core.audio_book.AudioBookDTO;
import ir.aliloc.api.core.book_index.BookIndex;
import ir.aliloc.api.core.book_index.BookIndexDTO;
import ir.aliloc.api.core.book_page.BookPage;
import ir.aliloc.api.core.book_page.BookPageDTO;
import ir.aliloc.api.core.certificate.Certificate;
import ir.aliloc.api.core.certificate.CertificateDTO;
import ir.aliloc.api.core.client_version.ClientVersionDTO;
import ir.aliloc.api.core.client_version.ClientVersionEnum;
import ir.aliloc.api.core.cours_category.CourseCategory;
import ir.aliloc.api.core.cours_category.CourseCategoryDTO;
import ir.aliloc.api.core.course.Course;
import ir.aliloc.api.core.course.CourseDTO;
import ir.aliloc.api.core.course.CourseWithoutRelationDTO;
import ir.aliloc.api.core.lecture.Lecture;
import ir.aliloc.api.core.lecture.LectureDTO;
import ir.aliloc.api.core.lecture_subject.LectureSubject;
import ir.aliloc.api.core.lecture_subject.LectureSubjectDTO;
import ir.aliloc.api.core.multimedia.MultiMedia;
import ir.aliloc.api.core.multimedia.MultiMediaDTO;
import ir.aliloc.api.core.quize.Quiz;
import ir.aliloc.api.core.quize.QuizDTO;
import ir.aliloc.api.core.rate_model.RateModelDTO;
import ir.aliloc.api.core.user_rate.UserRate;
import ir.aliloc.api.core.user_rate.UserRateDTO;
import ir.aliloc.api.core.book.Book;
import ir.aliloc.api.core.book.BookDTO;
import ir.aliloc.api.core.step.Step;
import ir.aliloc.api.core.step.StepDTO;
import ir.aliloc.api.core.user.models.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomMapperService {
    @Autowired
    private ModelMapper mModelMapper;

    public ClientVersionDTO clientVersionToClientVersionDTO(String message, String updateLink, ClientVersionEnum type, String latestVersionName) {
        ClientVersionDTO clientVersionDTO = new ClientVersionDTO();
        clientVersionDTO.setUpdateLink(updateLink);
        clientVersionDTO.setMessage(message);
        clientVersionDTO.setType(type);
        clientVersionDTO.setLatestVersionName(latestVersionName);
        return clientVersionDTO;
    }

    public List<UserRateDTO> rateListToRateDTOList(List<UserRate> userRateList) {
        List<UserRateDTO> userRateDTOList = new ArrayList<>();
        for (UserRate userRate : userRateList) {
            userRateDTOList.add(rateToRateDTO(userRate));
        }
        return userRateDTOList;
    }

    public UserRateDTO rateToRateDTO(UserRate userRate) {
        UserRateDTO userRateDTO = new UserRateDTO();
        userRateDTO.setId(userRate.getId());
        userRateDTO.setDescription(userRate.getDescription());
        userRateDTO.setRate(userRate.getRate());
        userRateDTO.setTime(userRate.getTime());
        RateModelDTO rateModelDTO = new RateModelDTO();
        rateModelDTO.setId(userRate.getRateModel().getId());
        rateModelDTO.setDescription(userRate.getRateModel().getDescription());
        rateModelDTO.setText(userRate.getRateModel().getText());
        rateModelDTO.setRate(userRate.getRateModel().getRate());
        rateModelDTO.setRateType(userRate.getRateModel().getRateType());
        userRateDTO.setRateModel(rateModelDTO);
        return userRateDTO;
    }

    public LectureSubjectDTO lectureSubjectToLectureSubjectDTO(LectureSubject lectureSubject) {
        LectureSubjectDTO lectureSubjectDTO = new LectureSubjectDTO();
        lectureSubjectDTO.setId(lectureSubject.getId());
        lectureSubjectDTO.setTitle(lectureSubject.getTitle());
        return lectureSubjectDTO;
    }

    public List<LectureSubjectDTO> lectureSubjectListToLectureSubjectDTOList(List<LectureSubject> lectureSubjects) {
        List<LectureSubjectDTO> lectureSubjectDTOList = new ArrayList<>();
        for (LectureSubject lectureSubject : lectureSubjects) {
            lectureSubjectDTOList.add(lectureSubjectToLectureSubjectDTO(lectureSubject));
        }
        return lectureSubjectDTOList;
    }

    public MultiMediaDTO multimediaToMultimediaDTO(MultiMedia multiMedia, boolean isShowUrl) {
        MultiMediaDTO multiMediaDTO = new MultiMediaDTO();
        multiMediaDTO.setId(multiMedia.getId());
        multiMediaDTO.setSize(multiMedia.getSize());
        multiMediaDTO.setQuality(multiMedia.getQuality());
        multiMediaDTO.setMime(multiMedia.getMime());
        multiMediaDTO.setDuration(multiMedia.getDuration());
        multiMediaDTO.setDescription(multiMedia.getDescription());
        if (isShowUrl) {
            multiMediaDTO.setUrl(multiMedia.getUrl());
        }
        return multiMediaDTO;
    }

    public List<MultiMediaDTO> multiMediaListToMultiMediaDTOList(List<MultiMedia> multiMediaList, boolean isShowUrl) {
        List<MultiMediaDTO> multiMediaDTOList = new ArrayList<>();
        for (MultiMedia multiMedia : multiMediaList) {
            multiMediaDTOList.add(multimediaToMultimediaDTO(multiMedia, isShowUrl));
        }
        return multiMediaDTOList;
    }

    public List<MultiMediaDTO> multiMediaListToMultiMediaUrlDTOList(Set<MultiMedia> multiMediaList, boolean isShowUrl) {
        List<MultiMediaDTO> multiMediaDTOList = new ArrayList<>();
        for (MultiMedia multiMedia : multiMediaList) {
            multiMediaDTOList.add(multimediaToMultimediaDTO(multiMedia, isShowUrl));
        }
        return multiMediaDTOList;
    }

    public LectureDTO lectureToLectureDTO(Lecture lecture, List<Long> downloadLectureIds) {
        LectureDTO lectureDTO = new LectureDTO();
        lectureDTO.setId(lecture.getId());
        lectureDTO.setTitle(lecture.getTitle());
        lectureDTO.setLectureSubject(lectureSubjectToLectureSubjectDTO(lecture.getLectureSubject()));
        if (lecture.getMultiMedia() != null) {
            lectureDTO.setMultiMedia(multimediaToMultimediaDTO(lecture.getMultiMedia(), true));
        }
        if (downloadLectureIds != null && downloadLectureIds.size() != 0) {
            for (Long id : downloadLectureIds) {
                if (id == lecture.getId()) {
                    lectureDTO.setDownload(true);
                    break;
                }
            }
        }
        return lectureDTO;
    }

    public List<LectureDTO> lectureListToLectureDTOList(List<Lecture> lectureList, List<Long> downloadLectureIds) {
        List<LectureDTO> lectureDTOList = new ArrayList<>();
        for (Lecture lecture : lectureList) {
            lectureDTOList.add(lectureToLectureDTO(lecture, downloadLectureIds));
        }
        return lectureDTOList;
    }

    public BookIndexDTO bookIndexToBookIndexDTO(BookIndex bookIndex) {
        BookIndexDTO bookIndexDTO = new BookIndexDTO();
        bookIndexDTO.setId(bookIndex.getId());
        bookIndexDTO.setPage(bookIndex.getPage());
        bookIndexDTO.setTitle(bookIndex.getTitle());
        bookIndexDTO.setLevel(bookIndex.getLevel());
        bookIndexDTO.setSubTitles(new ArrayList<>());
        return bookIndexDTO;
    }

    public List<BookIndexDTO> bookIndexListToBookIndexDTOList(Set<BookIndex> bookIndexList) {
        List<BookIndexDTO> bookIndexDTOList = new ArrayList<>();
        for (BookIndex bookIndex : bookIndexList) {
            bookIndexDTOList.add(bookIndexToBookIndexDTO(bookIndex));
        }
        return bookIndexDTOList;
    }

    public List<BookIndexDTO> bookIndexListToBookIndexDTOList(List<BookIndex> bookIndexList) {
        List<BookIndexDTO> bookIndexDTOList = new ArrayList<>();
        for (BookIndex bookIndex : bookIndexList) {
            bookIndexDTOList.add(bookIndexToBookIndexDTO(bookIndex));
        }
        return bookIndexDTOList;
    }

    public BookPageDTO bookPageToBookPageDTO(BookPage bookPage) {
        BookPageDTO bookPageDTO = new BookPageDTO();
        bookPageDTO.setPage(bookPage.getPage());
        bookPageDTO.setId(bookPage.getId());
        bookPageDTO.setPageNumber(bookPage.getPageNumber());
        bookPageDTO.setFoot(bookPage.getFoot());
        return bookPageDTO;
    }

    public List<BookPageDTO> bookPageListToBookPageDTOList(List<BookPage> bookPageList) {
        List<BookPageDTO> bookPageDTOList = new ArrayList<>();
        for (BookPage bookPage : bookPageList) {
            bookPageDTOList.add(bookPageToBookPageDTO(bookPage));
        }
        return bookPageDTOList;
    }

    public List<BookDTO> bookListToBookDTOList(List<Book> bookList) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookList) {
            bookDTOList.add(bookToBookDTO(book, null, null, null));
        }
        return bookDTOList;
    }


    public BookDTO audioBookToBookDTO(AudioBook audioBook, Boolean showUrl) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(audioBook.getBook().getId());
        bookDTO.setDescription(audioBook.getBook().getDescription());
        bookDTO.setPageCount(audioBook.getBook().getPageCount());
        bookDTO.setPrice(audioBook.getBook().getPrice());
        bookDTO.setPrintDate(audioBook.getBook().getPrintDate());
        bookDTO.setPrintOrder(audioBook.getBook().getPrintOrder());
        bookDTO.setTitle(audioBook.getBook().getTitle());
        bookDTO.setSellLink(audioBook.getBook().getSellLink());
        bookDTO.setStartPage(audioBook.getBook().getStartPage());
        bookDTO.setLectures(lectureListToLectureDTOList(audioBook.getBook().getLectures(), null));
        if (audioBook.getBook().getThumbnail() != null) {
            bookDTO.setThumbnail(multimediaToMultimediaDTO(audioBook.getBook().getThumbnail(), true));
        }
        AudioBookDTO audioDemo = new AudioBookDTO();
        audioDemo.setId(audioBook.getId());
        audioDemo.setTitle(audioBook.getTitle());
        audioDemo.setPrice(audioBook.getPrice());
        if (audioBook.getDemo() != null) {
            audioDemo.setDemo(multimediaToMultimediaDTO(audioBook.getDemo(), true));
        }
        if (audioBook.getMultimedia().size() != 0) {
            audioDemo.setMultimediaList(multiMediaListToMultiMediaUrlDTOList(audioBook.getMultimedia(), showUrl));
        } else {
            audioDemo.setMultimediaList(new ArrayList<>());
        }
        bookDTO.setAudio(audioDemo);
        return bookDTO;
    }

    public List<BookDTO> audioBookListToBookDTO(List<AudioBook> audioBookList, Boolean showUrl) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (AudioBook audioBook : audioBookList) {
            BookDTO bookDTO = audioBookToBookDTO(audioBook, showUrl);
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

    public AudioBookDTO audioBookToAudioBookDTO(AudioBook audioBook, List<Long> audioBookBuyIds) {
        boolean showUrl = false;
        AudioBookDTO audioBookDTO = new AudioBookDTO();
        audioBookDTO.setId(audioBook.getId());
        audioBookDTO.setPrice(audioBook.getPrice());
        audioBookDTO.setTitle(audioBook.getTitle());
        if (audioBook.getDemo() != null) {
            audioBookDTO.setDemo(multimediaToMultimediaDTO(audioBook.getDemo(), true));
        }
        if (audioBookBuyIds != null && audioBookBuyIds.size() != 0) {
            for (Long id : audioBookBuyIds) {
                if (audioBook.getId() == id) {
                    audioBookDTO.setBuy(true);
                    showUrl = true;
                    break;
                }
            }
        }
        if (audioBook.getPrice() == 0) {
            showUrl = true;
        }
        audioBookDTO.setMultimediaList(multiMediaListToMultiMediaUrlDTOList(audioBook.getMultimedia(), showUrl));
        return audioBookDTO;
    }

    public List<AudioBookDTO> audioBookListToAudioBookDTOList(List<AudioBook> audioBookList, List<Long> audioBookBuyIds) {
        List<AudioBookDTO> audioBookDTOList = new ArrayList<>();
        for (AudioBook audioBook : audioBookList) {
            audioBookDTOList.add(audioBookToAudioBookDTO(audioBook, audioBookBuyIds));
        }
        return audioBookDTOList;
    }

    public BookDTO bookToSimpleBookDTO(Book book) {
        return bookToBookDTO(book);

    }

    public BookDTO bookToBookDTO(Book book, List<Long> downloadBookIds, List<Long> downloadLectureIds, List<Long> audioBookIds) {
        BookDTO bookDTO = bookToBookDTO(book);
        if (downloadBookIds != null && downloadBookIds.size() != 0) {
            for (Long id : downloadBookIds) {
                if (id == book.getId()) {
                    bookDTO.setDownload(true);
                    break;
                }
            }
        }
        bookDTO.setLectures(lectureListToLectureDTOList(book.getLectures(), downloadLectureIds));
        if (book.getAudioBook() != null) {
            bookDTO.setAudio(audioBookToAudioBookDTO(book.getAudioBook(), audioBookIds));
        }
        return bookDTO;
    }

    private BookDTO bookToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setDescription(book.getDescription());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setStartPage(book.getStartPage());
        bookDTO.setPageCount(book.getPageCount());
        bookDTO.setSellLink(book.getSellLink());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setPrintDate(book.getPrintDate());
        bookDTO.setPrintOrder(book.getPrintOrder());
        if (book.getThumbnail() != null) {
            bookDTO.setThumbnail(multimediaToMultimediaDTO(book.getThumbnail(), true));
        }
        return bookDTO;
    }

    public List<BookDTO> bookListToBookDTOList(List<Book> bookList, List<Long> downloadBookIds, List<Long> downloadLectureIds, List<Long> audioBookIds) {
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (Book book : bookList) {
            bookDTOList.add(bookToBookDTO(book, downloadBookIds, downloadLectureIds, audioBookIds));
        }
        return bookDTOList;
    }

    public CourseDTO courseToCourseDTO(Course course, boolean showParent) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setTitle(course.getTitle());
        courseDTO.setTeacher(course.getTeacher());
        courseDTO.setPrice(course.getPrice());
        courseDTO.setExpireTime(course.getExpireTime());
        courseDTO.setMaxFeasibleDays(course.getMaxFeasibleDays());
        courseDTO.setMinDelayQuiz(course.getMinDelayQuiz());
        courseDTO.setPassGrade(course.getPassGrade());
        courseDTO.setPaymentType(course.getECoursePaymentType());
        courseDTO.setStartTime(course.getStartTime());
        if (course.getQuiz() != null) {
            courseDTO.setQuizDTO(mModelMapper.map(course.getQuiz(), QuizDTO.class));
        }
        if (course.getMultiMedia() != null) {
            courseDTO.setMultimedia(multimediaToMultimediaDTO(course.getMultiMedia(), true));
        }
        if (course.getPreCourse() != null && showParent) {
            courseDTO.setPreCourse(courseToCourseDTO(course.getPreCourse(), false));
        }
        if (course.getExpireTime() < Calendar.getInstance().getTimeInMillis()) {
            courseDTO.setExpired(true);
        }
        return courseDTO;
    }

    public List<CourseDTO> courseListToCourseDTOList(List<Course> courseList) {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        for (Course course : courseList) {
            courseDTOList.add(courseToCourseDTO(course, true));
        }
        return courseDTOList;
    }


    public QuizDTO quizToQuizDTO(Quiz quiz) {
        QuizDTO quizDTO = new QuizDTO();
        quizDTO.setId(quiz.getId());
        quizDTO.setCoefficient(quiz.getCoefficient());
        quizDTO.setPassPoint(quiz.getPassPoint());
        quizDTO.setQuestionCount(quiz.getQuestionCount());
        quizDTO.setTime(quiz.getTime());
        return quizDTO;
    }

    public StepDTO stepToStepDTO(Step step, boolean showPreStep) {
        StepDTO stepDTO = new StepDTO();
        stepDTO.setId(step.getId());
        stepDTO.setTitle(step.getTitle());
        stepDTO.setText(step.getText());
        stepDTO.setDescription(step.getDescription());
        List<MultiMediaDTO> multiMediaDTOS = new ArrayList<>();
        for (MultiMedia multiMedia : step.getMultimedia()) {
            MultiMediaDTO multiMediaDTO = multimediaToMultimediaDTO(multiMedia, true);
            multiMediaDTOS.add(multiMediaDTO);
        }
        stepDTO.setMultimedias(multiMediaDTOS);
        stepDTO.setCourse(courseToCourseDTO(step.getCourse(), true));
        if (step.getPreStep() != null && showPreStep) {
            stepDTO.setPreStep(stepToStepDTO(step.getPreStep(), false));
        }
        if (step.getQuiz() != null) {
            stepDTO.setQuiz(quizToQuizDTO(step.getQuiz()));
        }
        stepDTO.setBook(bookToSimpleBookDTO(step.getBook()));
        stepDTO.setBookIndexList(bookIndexListToBookIndexDTOList(step.getBookIndexSet()));
        return stepDTO;
    }

    public List<StepDTO> stepListToStepDTOList(List<Step> stepList) {
        List<StepDTO> stepDTOList = new ArrayList<>();
        for (Step step : stepList) {
            stepDTOList.add(stepToStepDTO(step, true));
        }
        return stepDTOList;
    }

    public List<CourseCategoryDTO> courseCategoryListToCourseCategoryDTOList(List<CourseCategory> courseCategories) {
        List<CourseCategoryDTO> courseCategoryDTOS = new ArrayList<>();
        Integer count;
        for (CourseCategory courseCategory : courseCategories) {
            CourseCategoryDTO courseCategoryDTO = mModelMapper.map(courseCategory, CourseCategoryDTO.class);
            List<CourseDTO> coursesDto = new ArrayList<>();
            count = 0;
            for (Course mCours : courseCategory.getMCourses()) {
                count = ++count;
                if (count <= 10) {

                    coursesDto.add(courseToCourseDTO(mCours, false));
                } else {
                    break;
                }
            }
            courseCategoryDTO.setCourseList(coursesDto);
            courseCategoryDTOS.add(courseCategoryDTO);
        }
        return courseCategoryDTOS;
    }

    public List<CertificateDTO> certificateListToCertificateDTOList(List<Certificate> certificates) {
        List<CertificateDTO> certificateDTOS = new ArrayList<>();
        for (Certificate certificate : certificates) {
            certificateDTOS.add(certificateToCertificateDTO(certificate));
        }
        return certificateDTOS;
    }

    public CertificateDTO certificateToCertificateDTO(Certificate certificate) {
        CertificateDTO certificateDTO = mModelMapper.map(certificate, CertificateDTO.class);
        UserDTO userDTO = mModelMapper.map(certificate.getUser(), UserDTO.class);
        CourseWithoutRelationDTO courseWithoutRelationDTO = mModelMapper.map(certificate.getCourse(), CourseWithoutRelationDTO.class);
        certificateDTO.setUser(userDTO);
        certificateDTO.setCourse(courseWithoutRelationDTO);
        return certificateDTO;
    }

    public List<LectureSubjectDTO> lectureSubjectListToLectureSubjectDTOListWhitLecture(List<LectureSubject> lectureSubjects) {
        List<LectureSubjectDTO> lectureSubjectDTOS = new ArrayList<>();
        for (LectureSubject lectureSubject : lectureSubjects) {
            LectureSubjectDTO lectureSubjectDTO = mModelMapper.map(lectureSubject, LectureSubjectDTO.class);
//            lectureSubjectDTO.setLectures(lectureListToLectureDTOList());
            for (LectureDTO lectureDTO : lectureSubjectDTO.getLectures()) {
                lectureDTO.setLectureSubject(null);
            }
            lectureSubjectDTOS.add(lectureSubjectDTO);
        }
        return lectureSubjectDTOS;
    }

    public List<LectureSubjectDTO> lectureSubjectListToLectureSubjectDTOListWhitLecture(List<LectureSubject> lectureSubjects, HashSet<Long> lectureDownloaded) {
        List<LectureSubjectDTO> lectureSubjectDTOS = new ArrayList<>();
        for (LectureSubject lectureSubject : lectureSubjects) {
            LectureSubjectDTO lectureSubjectDTO = mModelMapper.map(lectureSubject, LectureSubjectDTO.class);
            for (LectureDTO lectureDTO : lectureSubjectDTO.getLectures()) {
                if (lectureDownloaded.contains(lectureDTO.getId())) {
                    lectureDTO.setDownload(true);
                } else {
                    lectureDTO.setDownload(false);
                }
                lectureDTO.setLectureSubject(null);
            }
            lectureSubjectDTOS.add(lectureSubjectDTO);
        }
        return lectureSubjectDTOS;
    }

    public List<BookDTO> audioBookListToAudioBookDTOList(List<AudioBook> audioBookList, HashSet<Long> purchasedAudioBookIdsSet) {
        List<BookDTO> bookDTOS = audioBookListToBookDTO(audioBookList, true);
        for (BookDTO bookDTO : bookDTOS) {
            if (purchasedAudioBookIdsSet.contains(bookDTO.getAudio().getId())) {
                bookDTO.getAudio().setBuy(true);
            } else {
                bookDTO.getAudio().setBuy(false);
                for (MultiMediaDTO multiMediaDTO : bookDTO.getAudio().getMultimediaList()) {
                    multiMediaDTO.setUrl(null);
                }
            }
        }
        return bookDTOS;
    }
}

