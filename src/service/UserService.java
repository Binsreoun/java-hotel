package service;

import model.User;

public class UserService {

    public void chargePoint(User user, int point) {
        user.setPoint(user.getPoint() + point);
    }
}
