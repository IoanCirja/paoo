package PaooGame.Items;


//pt a identifica usor animatiile in restul codului
public class Constants {
    public static class PlayerConstants {
        public static final int IDLE = 11;
        public static final int RUNNING = 14;
        public static final int JUMP = 12;
        public static final int FALLING = 9;
        public static final int SLIDE = 3;
        public static final int SLASH = 0;
        //public static final int AIR_THROW = 2;
        public static final int RUN_SLASH = 2;
        public static final int AIR_SLASH = 4;
        public static final int AIR_THROW = 5;
        public static final int CLIMB = 6;
        public static final int DIE = 7;
        public static final int DOUBLE_JUMP = 8;
        public static final int HURT = 1;
        public static final int KICK = 13;
        public static final int THROW = 15;
        public static final int WALK = 16;


        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case IDLE:
                case CLIMB:
                case WALK:
                    return 18;
                case RUNNING:

                case SLASH:
                case AIR_SLASH:
                case AIR_THROW:

                case HURT:
                case KICK:
                case JUMP:
                case THROW:
                    return 12;

                case SLIDE:
                case DOUBLE_JUMP:
                    return 6;
                //case AIR_THROW = 2;
                case RUN_SLASH:
                    return 12;
                case DIE:
                    return 15;

                case FALLING:
                    return 5;
                default:
                    return 1;
            }
        }
    }

    public static class ArcherConstants {
        public static final int DIE1 = 2;
        public static final int SHOOT1 = 1;
        public static final int IDLE1 = 0;
        public static final int HURT = 3;

        public static int GetSpriteAmount1(int player_action) {
            switch (player_action) {
                case DIE1:
                case SHOOT1:
                case IDLE1:
                case HURT:
                    return 15;
                default:
                    return 1;

            }

        }
    }




    public static class MagicianConstants {
        public static final int DIE2 = 2;
        public static final int SLASH2 = 1;
        public static final int IDLE2 = 0;
        public static final int WALK2 = 3;


        public static int GetSpriteAmount2(int player_action) {
            switch (player_action) {
                case DIE2:
                case SLASH2:
                case IDLE2:
                case WALK2:
                    return 15;
                default:
                    return 1;

            }

        }
    }
}


