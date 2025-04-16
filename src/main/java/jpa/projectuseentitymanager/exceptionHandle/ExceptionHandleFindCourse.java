package jpa.projectuseentitymanager.exceptionHandle;

public class ExceptionHandleFindCourse extends RuntimeException  {
    public ExceptionHandleFindCourse(int courseId ) {
        super("Course with ID " + courseId + " not found.");
    }
}
