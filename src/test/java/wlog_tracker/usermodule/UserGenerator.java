package wlog_tracker.usermodule;

import wlog_tracker.usermodule.model.Title;
import wlog_tracker.usermodule.model.User;

public class UserGenerator {
    public static User createUser(String name, String surname, String pesel, String imageUrl, Title title) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setTitle(title);
        user.setPesel(pesel);
        user.setImage(imageUrl);

        return user;
    }

    public static User createUser(String name, String surname, String pesel, Title title) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setTitle(title);
        user.setPesel(pesel);

        return user;
    }

    public static User createCompleteUser() {
        User user = new User();
        user.setName("Denis");
        user.setSurname("Smith");
        user.setTitle(Title.TESTER);
        user.setPesel("49134829891");
        user.setImage("pl.pumbakos");
//        user.setVacations(Arrays.asList(
//                        VacationGenerator.createCompleteVacation(),
//                        VacationGenerator.createAnotherCompleteVacation()
//                )
//        );

        return user;
    }

    public static User createCompleteUserWithoutImageUrl() {
        User user = new User();
        user.setName("Denis");
        user.setSurname("Smith");
        user.setTitle(Title.TESTER);
        user.setPesel("49134829891");
//        user.setVacations(Arrays.asList(
//                        VacationGenerator.createCompleteVacation(),
//                        VacationGenerator.createAnotherCompleteVacation()
//                )
//        );

        return user;
    }

    public static User createBlankUser() {
        User user = new User();
        return new User();
    }

    public static User createEmptyValuesUser() {
        User user = new User();
        user.setName("");
        user.setSurname("");
        user.setPesel("");
        user.setImage("");
//        user.setVacations(List.of());

        return user;
    }
}
