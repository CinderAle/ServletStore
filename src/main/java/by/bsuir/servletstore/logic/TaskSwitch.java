package by.bsuir.servletstore.logic;

import by.bsuir.servletstore.logic.tasks.*;

import java.util.HashMap;

public final class TaskSwitch {
    private final HashMap<TaskEnum, ITask> taskHashMap = new HashMap<>();
    public TaskSwitch() {
        taskHashMap.put(TaskEnum.ADD_CART_ITEM, new AddCartItemTask());
        taskHashMap.put(TaskEnum.ADD_COUPON, new AddCouponTask());
        taskHashMap.put(TaskEnum.ADD_PRODUCT, new AddProductTask());
        taskHashMap.put(TaskEnum.ADD_SALE, new AddSaleTask());
        taskHashMap.put(TaskEnum.ADD_USER_COUPON, new AddUserCouponTask());
        taskHashMap.put(TaskEnum.EDIT_CART_ITEM, new EditCartItemTask());
        taskHashMap.put(TaskEnum.EDIT_COUPON, new EditCouponTask());
        taskHashMap.put(TaskEnum.EDIT_PRODUCT, new EditProductTask());
        taskHashMap.put(TaskEnum.EDIT_SALE, new EditSaleTask());
        taskHashMap.put(TaskEnum.EDIT_USER, new EditUserTask());
        taskHashMap.put(TaskEnum.GET_COUPONS, new GetCouponsTask());
        taskHashMap.put(TaskEnum.GET_COUPON, new GetCouponTask());
        taskHashMap.put(TaskEnum.GET_PRODUCTS, new GetProductsTask());
        taskHashMap.put(TaskEnum.GET_PRODUCT, new GetProductTask());
        taskHashMap.put(TaskEnum.GET_SALES, new GetSalesTask());
        taskHashMap.put(TaskEnum.GET_SALE, new GetSaleTask());
        taskHashMap.put(TaskEnum.GET_USER_CART, new GetUserCartTask());
        taskHashMap.put(TaskEnum.GET_USER_COUPON, new GetUserCouponTask());
        taskHashMap.put(TaskEnum.DO_LOGIN, new LoginTask());
        taskHashMap.put(TaskEnum.DO_LOGOUT, new LogoutTask());
        taskHashMap.put(TaskEnum.DO_REGISTER, new RegisterTask());
        taskHashMap.put(TaskEnum.REMOVE_CART_ITEM, new RemoveCartItemTask());
        taskHashMap.put(TaskEnum.REMOVE_CART, new RemoveCartTask());
        taskHashMap.put(TaskEnum.REMOVE_COUPON, new RemoveCouponTask());
        taskHashMap.put(TaskEnum.REMOVE_PRODUCT, new RemoveProductTask());
        taskHashMap.put(TaskEnum.REMOVE_SALE, new RemoveSaleTask());
        taskHashMap.put(TaskEnum.REMOVE_USER_COUPON, new RemoveUserCouponTask());
        taskHashMap.put(TaskEnum.UNKNOWN_TASK, new UnknownTask());
    }

    public static TaskSwitch getInstance() {
        return new TaskSwitch();
    }

    public ITask getTask(String taskName) {
        return taskHashMap.get(taskName);
    }
}
