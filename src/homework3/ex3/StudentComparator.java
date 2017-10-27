package homework3.ex3;

import homework3.ex2.Student;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {
    private Group.SortField field;
    private Group.SortOrder order;

    public StudentComparator(Group.SortField field, Group.SortOrder order) {

        this.field = field;
        this.order = order;
    }

    @Override
    public int compare(Student student1, Student student2) {
        int result = 0;

        switch (field) {
            case LASTNAME: {
                result = student1.getLastName()
                        .compareToIgnoreCase(student2.getLastName());
                break;
            }
            case FIRSTNAME: {
                result = student1.getFirstName()
                        .compareToIgnoreCase(student2.getFirstName());
                break;
            }
            case MIDDLENAME: {
                result = student1.getMiddleName()
                        .compareToIgnoreCase(student2.getMiddleName());
                break;
            }
            case AGE: {
                result = student1.getAge() - student2.getAge();
                break;
            }
            case SEX: {
                result = (!student1.isSex() ? 0 : 1) - (!student2.isSex() ? 0 : 1);
                break;
            }
        }


        return result * order.getOrder();
    }
}
