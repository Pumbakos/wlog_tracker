package wlog_tracker.usermodule;

import wlog_tracker.usermodule.model.Title;
import wlog_tracker.usermodule.model.User;

class UserGenerator {
    User createUser(String name, String surname, String pesel, String imageUrl, Title title){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setTitle(title);
        user.setPesel(pesel);
        user.setImageUrl(imageUrl);

        return user;
    }

    User createUser(String name, String surname, String pesel, Title title){
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setTitle(title);
        user.setPesel(pesel);

        return user;
    }

    User createCompleteUser(){
        User user = new User();
        user.setName("Denis");
        user.setSurname("Smith");
        user.setTitle(Title.TESTER);
        user.setPesel("49134829891");
        user.setImageUrl("pl.pumbakos");

        return user;
    }

    User createCompleteUserWithoutImageUrl(){
        User user = new User();
        user.setName("Denis");
        user.setSurname("Smith");
        user.setTitle(Title.TESTER);
        user.setPesel("49134829891");

        return user;
    }

    User createBlankUser(){
        User user = new User();
        return new User();
    }

    User createEmptyValuesUser(){
        User user = new User();
        user.setName("");
        user.setSurname("");
        user.setPesel("");
        user.setImageUrl("");

        return user;
    }
}
