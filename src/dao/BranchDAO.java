package dao;

public interface BranchDAO extends Dao{
    static BranchDAO getInstance() {
        return new BranchDAOImpl();
    }

    void displayBranchInfo();

}
