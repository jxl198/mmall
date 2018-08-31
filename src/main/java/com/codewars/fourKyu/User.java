package com.codewars.fourKyu;

/**
 * @author jiangxl
 * @description 级别计算系统
 * @date 2018-07-12 16:06
 **/
public class User {
    public  int rank ;   //用户的等级
    public  int progress;   // 用户当前等级积分

    public User(){
        this.rank=-8;
        this.progress=0;
    }
    /**
     * @param activityRank 题目级别
     * @return
     */
    public int incProgress(int activityRank) throws IllegalArgumentException{
        //题目等级小于当前用户等级一个级别，积分+1
        System.out.println(activityRank);
        if(activityRank<-8||activityRank>8||activityRank==0){
            throw new IllegalArgumentException("error argument");
        }

        int result = 0;
        if (activityRank < this.rank) {
            if ((this.rank - activityRank == 1) || (this.rank == 1 && activityRank == -1)) {
                result = 1;
            }

        } else if (this.rank == activityRank) {
            result = 3;

        } else {
            int diff;
            if (this.rank < 0 && activityRank > 0) {
                diff = activityRank - this.rank - 1;
            } else {
                diff = activityRank - this.rank;
            }
            result = 10 * diff * diff;

        }
        updateUserInfo(result);
        return result;

    }


    public void updateUserInfo(int score) {
        progress = progress + score;

        if (progress >= 100) {
            int rankNum = progress / 100;
            rank = rank + rankNum;
            if (rank == 0) {
                rank = 1;
            }
            progress = progress % 100;

        }

        if(rank>=8){
            rank =8;
            progress=0;
        }
    }

    public void init (){
        this.progress=0;
        this.rank=-8;
    }



    public static void main(String[] args) {
        User user = new User();
//        user.incProgress(-7);
//        System.out.println(user.rank+":"+user.progress);
//
//        user.incProgress(-5);
//        System.out.println(user.rank+":"+user.progress);

//        user.incProgress(-8);
//        System.out.println(user.rank+":"+user.progress);
//        user.init();
//        user.incProgress(-7);
//        System.out.println(user.rank+":"+user.progress);
//        user.init();
//        user.incProgress(-6);
//        System.out.println(user.rank+":"+user.progress);
//        user.init();
//        user.incProgress(-5);
//        System.out.println(user.rank+":"+user.progress);
//        user.init();
//        user.incProgress(-4);
//        System.out.println(user.rank+":"+user.progress);
//        user.init();
//        user.incProgress(-8);
//        System.out.println(user.rank+":"+user.progress);
        user.init();
        user.incProgress(1);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(1);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(1);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(1);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(1);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(2);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(2);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(-1);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(3);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(8);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(8);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(8);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(8);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(8);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(8);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(8);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(8);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(8);
        System.out.println(user.rank+":"+user.progress);

        user.incProgress(8);
        System.out.println(user.rank+":"+user.progress);
        user.incProgress(8);
        System.out.println(user.rank+":"+user.progress);


//        test.assert_equals(user.rank(), -8, 'User rank should be -8')
//        test.assert_equals(user.progress(), 0, 'User progress should be 0')
//        user.inc_progress(-7)
//        test.assert_equals(user.rank(), -8, 'User rank should be -8')
//        test.assert_equals(user.progress(), 10, 'User progress should be 10')
//        user.inc_progress(-5)
//        test.assert_equals(user.rank(), -7, 'User rank should be -7')
//        test.assert_equals(user.progress(), 0, 'User progress should be 0')
//        user.incProgress(-8);


    }
}
