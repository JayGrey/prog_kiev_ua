package homework3.ex3;

import homework3.ex2.Student;

import java.util.Comparator;

public class StudentComparator implements Comparator {
    private int sortingField;
    private int sortingOrder;

    public StudentComparator(int sortingField, int sortingOrder) {

        if (sortingField != Group.SORT_BY_LASTNAME
                && sortingField != Group.SORT_BY_FIRSTNAME
                && sortingField != Group.SORT_BY_MIDDLENAME
                && sortingField != Group.SORT_BY_AGE
                && sortingField != Group.SORT_BY_SEX) {
            throw new IllegalArgumentException("unknown sorting field");
        }
        if (sortingOrder != Group.ACSENDING_ORDER
                && sortingOrder != Group.DESCENDING_ORDER) {
            throw new IllegalArgumentException("unknown sorting order");
        }
        this.sortingField = sortingField;
        this.sortingOrder = sortingOrder;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Student student1 = (Student) o1;
        Student student2 = (Student) o2;

        int result = 0;

        switch (sortingField) {
            case Group.SORT_BY_LASTNAME: {
                result = student1.getLastName()
                        .compareToIgnoreCase(student2.getLastName());
                break;
            }
            case Group.SORT_BY_FIRSTNAME: {
                result = student1.getFirstName()
                        .compareToIgnoreCase(student2.getFirstName());
                break;
            }
            case Group.SORT_BY_MIDDLENAME: {
                result = student1.getMiddleName()
                        .compareToIgnoreCase(student2.getMiddleName());
                break;
            }
            case Group.SORT_BY_AGE: {
                result = student1.getAge() - student2.getAge();
                break;
            }
            case Group.SORT_BY_SEX: {
                result = (!student1.isSex() ? 0 : 1) - (!student2.isSex() ? 0 : 1);
                break;
            }
        }

        if (sortingOrder == Group.ACSENDING_ORDER) {
            return result;
        }
        return -result;
    }
}
