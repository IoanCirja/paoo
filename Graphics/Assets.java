package PaooGame.Graphics;

import java.awt.image.BufferedImage;
//clasa pentru incarcarea imagilor in program, are try/catch, foloseste clase de tratare a exceptiilor imageloader si spritesheet
public class Assets
{
    public static BufferedImage[][] sifAnimations = new BufferedImage[17][18];
    public static BufferedImage[][] boss = new BufferedImage[4][15];
    public static BufferedImage meteor;
    public  static BufferedImage end;
    public static BufferedImage pause;
    public static BufferedImage scores;
    public static BufferedImage load;
    public static BufferedImage x;
    public static BufferedImage red_ball;
    public static BufferedImage[][] archer1 = new BufferedImage[3][15];
    public static BufferedImage[][] magician1 = new BufferedImage[4][15];
    public static BufferedImage[][] kicker = new BufferedImage[3][15];
    public static BufferedImage[][] karp = new BufferedImage[3][15];
    public static BufferedImage what;
    public static BufferedImage curse;
    public static BufferedImage dead;
    public static BufferedImage hah;
    public static BufferedImage arrow1;
    public static BufferedImage shield;
    public static BufferedImage[] coins = new BufferedImage[6];
    public static BufferedImage bubble_green;
    public static BufferedImage green_ball;

    //lvl 1
    public static BufferedImage bonus;
    public static BufferedImage brick_1;
    public static BufferedImage cage;
    public static BufferedImage chains;
    public static BufferedImage column;
    public static BufferedImage fence;
    public static BufferedImage ground_2;
    public static BufferedImage ground_4;
    public static BufferedImage ground_6;
    public static BufferedImage menu;
    public static BufferedImage ground_8;
    public static BufferedImage ground_9;
    public static BufferedImage ground_10;
    public static BufferedImage ground_11;
    public static BufferedImage ground_12;
    public static BufferedImage ground_13;
    public static BufferedImage ground_ladder;
    public static BufferedImage ladder;
    public static BufferedImage lantern;
    public static BufferedImage rock;
    public static BufferedImage light;
    public static BufferedImage skulls;
    public static BufferedImage grey;
    public static BufferedImage sign_1;
    public static BufferedImage sign_2;
    public static BufferedImage star;
    public static BufferedImage sign_3;
    public static BufferedImage sign_4;
    public static BufferedImage sign_5;
    public static BufferedImage sign_6;
    public static BufferedImage life;
    public static BufferedImage spikes;
    public static BufferedImage statue;
    public static BufferedImage wooden_barrel;
    public static BufferedImage wooden_box;


    //lvl 2
    public static BufferedImage lvl2_bonus;
    public static BufferedImage lvl2_brick_1;
    public static BufferedImage lvl2_pick;
    public static BufferedImage lvl2_mushroom;
    public static BufferedImage lvl2_goldrock;
    public static BufferedImage lvl2_fence;
    public static BufferedImage lvl2_ground_2;
    public static BufferedImage lvl2_ground_4;
    public static BufferedImage lvl2_ground_6;

    public static BufferedImage lvl2_ground_8;
    public static BufferedImage lvl2_ground_9;
    public static BufferedImage lvl2_ground_10;
    public static BufferedImage lvl2_ground_11;
    public static BufferedImage lvl2_ground_12;
    public static BufferedImage lvl2_ground_13;
    public static BufferedImage bubble;
    public static BufferedImage lvl2_ground_ladder;
    public static BufferedImage lvl2_ladder;
    public static BufferedImage lvl2_lantern;
    public static BufferedImage lvl2_greenrock;
    public static BufferedImage lvl3_lantern;

    public static BufferedImage lvl2_smallrock;
    public static BufferedImage lvl2_sign_1;
    public static BufferedImage lvl2_sign_2;

    public static BufferedImage lvl2_sign_3;
    public static BufferedImage lvl2_sign_4;
    public static BufferedImage lvl2_sign_5;
    public static BufferedImage lvl2_sign_6;
    public static BufferedImage lvl2_life;
    public static BufferedImage lvl2_spikes;
    public static BufferedImage lvl2_statue;
    public static BufferedImage lvl2_wooden_barrel;
    public static BufferedImage lvl2_wooden_box;

    //lvl3

    public static BufferedImage lvl3_bonus;
    public static BufferedImage lvl3_brick_1;
    public static BufferedImage lvl3_pick;
    public static BufferedImage lvl3_mushroom;
    public static BufferedImage lvl3_goldrock;
    public static BufferedImage lvl3_fence;
    public static BufferedImage lvl3_ground_2;
    public static BufferedImage lvl3_ground_4;
    public static BufferedImage lvl3_ground_6;

    public static BufferedImage lvl3_ground_8;
    public static BufferedImage lvl3_ground_9;
    public static BufferedImage lvl3_ground_10;
    public static BufferedImage lvl3_ground_11;
    public static BufferedImage lvl3_ground_12;
    public static BufferedImage lvl3_ground_13;

    public static BufferedImage lvl3_ground_ladder;
    public static BufferedImage lvl3_ladder;

    public static BufferedImage lvl3_greenrock;

    public static BufferedImage lvl3_smallrock;
    public static BufferedImage lvl3_sign_1;
    public static BufferedImage lvl3_sign_2;

    public static BufferedImage lvl3_sign_3;
    public static BufferedImage lvl3_sign_4;
    public static BufferedImage lvl3_sign_5;
    public static BufferedImage lvl3_sign_6;

    public static BufferedImage lvl3_spikes;
    public static BufferedImage lvl3_statue;
    public static BufferedImage lvl3_wooden_barrel;
    public static BufferedImage lvl3_wooden_box;





    public static BufferedImage invisible;




    public static void Init()
    {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/entities/sif.png"));

        for (int j = 0; j < sifAnimations.length; j++) {
            for (int i = 0; i < sifAnimations[j].length; i++) {
                sifAnimations[j][i] = sheet.crop_900(i, j);
            }
        }

        sheet = new SpriteSheet(ImageLoader.LoadImage("/level_1/Environment/level_1.png"));


        bonus = sheet.crop(10, 0);
        brick_1 = sheet.crop(11, 0);
        cage = sheet.crop(15, 0);
        chains = sheet.crop(16, 0);
        column = sheet.crop(17, 0);
        fence = sheet.crop(19, 0);
        ground_2 = sheet.crop(4, 0);
        ground_4 = sheet.crop(3, 0);
        ground_6 = sheet.crop(8, 0);
        ground_8 = sheet.crop(5, 0);
        ground_9 = sheet.crop(7, 0);

        ground_10 = sheet.crop(0, 0);
        ground_11 = sheet.crop(1, 0);
        ground_12 = sheet.crop(2, 0);


        ground_13 = sheet.crop(9, 0);

        ground_ladder = sheet.crop(6, 0);
        ladder = sheet.crop(13, 0);
        lantern = sheet.crop(21, 0);
        rock = sheet.crop(20, 0);
        skulls = sheet.crop(18, 0);
        sign_1 = sheet.crop(22, 0);
        sign_2 = sheet.crop(23, 0);
        sign_3 = sheet.crop(24, 0);
        sign_4 = sheet.crop(25, 0);
        sign_5 = sheet.crop(26, 0);
        sign_6 = sheet.crop(27, 0);
        spikes = sheet.crop(28, 0);
        statue = sheet.crop(29, 0);
        wooden_barrel = sheet.crop(14, 0);
        wooden_box = sheet.crop(12, 0);
        invisible = sheet.crop(30,0 );


        sheet = new SpriteSheet(ImageLoader.LoadImage("/level_2/level_2.png"));

        lvl2_bonus = sheet.crop(10, 0);
        lvl2_brick_1 = sheet.crop(11, 0);
        lvl2_pick = sheet.crop(15, 0);
        lvl2_mushroom = sheet.crop(16, 0);
        lvl2_goldrock = sheet.crop(17, 0);
        lvl2_fence = sheet.crop(19, 0);
        lvl2_ground_2 = sheet.crop(4, 0);
        lvl2_ground_4 = sheet.crop(3, 0);
        lvl2_ground_6 = sheet.crop(8, 0);
        lvl2_ground_8 = sheet.crop(5, 0);
        lvl2_ground_9 = sheet.crop(7, 0);
        lvl2_ground_10 = sheet.crop(0, 0);
        lvl2_ground_11 = sheet.crop(1, 0);
        lvl2_ground_12 = sheet.crop(2, 0);
        lvl2_ground_13 = sheet.crop(9, 0);
        lvl2_ground_ladder = sheet.crop(6, 0);
        lvl2_ladder = sheet.crop(13, 0);
        lvl2_lantern = sheet.crop(21, 0);
        lvl2_greenrock = sheet.crop(20, 0);
        lvl2_smallrock = sheet.crop(18, 0);
        lvl2_sign_1 = sheet.crop(22, 0);
        lvl2_sign_2 = sheet.crop(23, 0);
        lvl2_sign_3 = sheet.crop(24, 0);
        lvl2_sign_4 = sheet.crop(25, 0);
        lvl2_sign_5 = sheet.crop(26, 0);
        lvl2_sign_6 = sheet.crop(27, 0);
        lvl2_spikes = sheet.crop(28, 0);
        lvl2_statue = sheet.crop(29, 0);
        lvl2_wooden_barrel = sheet.crop(14, 0);
        lvl2_wooden_box = sheet.crop(12, 0);




        sheet = new SpriteSheet(ImageLoader.LoadImage("/level_3/level_3.png"));


        lvl3_bonus = sheet.crop(10, 0);
        lvl3_brick_1 = sheet.crop(11, 0);
        lvl3_pick = sheet.crop(15, 0);
        lvl3_mushroom = sheet.crop(16, 0);
        lvl3_goldrock = sheet.crop(17, 0);
        lvl3_fence = sheet.crop(19, 0);
        lvl3_ground_2 = sheet.crop(4, 0);
        lvl3_ground_4 = sheet.crop(3, 0);
        lvl3_ground_6 = sheet.crop(8, 0);
        lvl3_ground_8 = sheet.crop(5, 0);
        lvl3_ground_9 = sheet.crop(7, 0);
        lvl3_ground_10 = sheet.crop(0, 0);
        lvl3_ground_11 = sheet.crop(1, 0);
        lvl3_ground_12 = sheet.crop(2, 0);
        lvl3_ground_13 = sheet.crop(9, 0);
        lvl3_ground_ladder = sheet.crop(6, 0);
        lvl3_ladder = sheet.crop(13, 0);
        lvl3_lantern = sheet.crop(21, 0);
        lvl3_greenrock = sheet.crop(20, 0);
        lvl3_smallrock = sheet.crop(18, 0);
        lvl3_sign_1 = sheet.crop(22, 0);
        lvl3_sign_2 = sheet.crop(23, 0);
        lvl3_sign_3 = sheet.crop(24, 0);
        lvl3_sign_4 = sheet.crop(25, 0);
        lvl3_sign_5 = sheet.crop(26, 0);
        lvl3_sign_6 = sheet.crop(27, 0);
        lvl3_spikes = sheet.crop(28, 0);
        lvl3_statue = sheet.crop(29, 0);
        lvl3_wooden_barrel = sheet.crop(14, 0);
        lvl3_wooden_box = sheet.crop(12, 0);


        sheet = new SpriteSheet(ImageLoader.LoadImage("/level_1/Collectable Object/coins.png"));

        for(int i =0 ;i<6;i++)
        {
            coins[i] = sheet.crop(i, 0);
        }


        sheet = new SpriteSheet(ImageLoader.LoadImage("/entities/archer1.png"));

        for (int j = 0; j < archer1.length; j++) {
            for (int i = 0; i < archer1[j].length; i++) {
                archer1[j][i] = sheet.crop_900(i, j);
            }
        }
        sheet = new SpriteSheet(ImageLoader.LoadImage("/entities/Karp.png"));

        for (int j = 0; j < karp.length; j++) {
            for (int i = 0; i < karp[j].length; i++) {
                karp[j][i] = sheet.crop_900(i, j);
            }
        }


        sheet = new SpriteSheet(ImageLoader.LoadImage("/entities/boss.png"));

        for (int j = 0; j < boss.length; j++) {
            for (int i = 0; i < boss[j].length; i++) {
                boss[j][i] = sheet.crop_900(i, j);
            }
        }



        sheet = new SpriteSheet(ImageLoader.LoadImage("/entities/magician1.png"));

        for (int j = 0; j < magician1.length; j++) {
            for (int i = 0; i < magician1[j].length; i++) {
                magician1[j][i] = sheet.crop_900(i, j);
            }
        }

        sheet = new SpriteSheet(ImageLoader.LoadImage("/entities/kicker.png"));

        for (int j = 0; j < kicker.length; j++) {
            for (int i = 0; i < kicker[j].length; i++) {
                kicker[j][i] = sheet.crop_900(i, j);
            }
        }


        arrow1 = ImageLoader.LoadImage("/level_1/Collectable Object/Arrow1.png");
        grey = ImageLoader.LoadImage("/ui/grey.png");
        life = ImageLoader.LoadImage("/ui/Life.png");
        star = ImageLoader.LoadImage("/level_1/Collectable Object/Star.png");
        light = ImageLoader.LoadImage("/level_1/Collectable Object/Light.png");
        menu = ImageLoader.LoadImage("/ui/menu.png");
        bubble = ImageLoader.LoadImage("/level_1/Collectable Object/Bubble.png");
        bubble_green = ImageLoader.LoadImage("/level_1/Collectable Object/bubble_green.png");
        green_ball = ImageLoader.LoadImage("/level_1/Collectable Object/Ball.png");
        red_ball = ImageLoader.LoadImage("/level_1/Collectable Object/Bubble_red.png");
        shield = ImageLoader.LoadImage("/level_1/Collectable Object/Shield.png");
        lvl3_lantern = ImageLoader.LoadImage("/level_1/Collectable Object/Lantern.png");
        what = ImageLoader.LoadImage("/ui/what.png");
        hah = ImageLoader.LoadImage("/ui/hah.png");
        curse = ImageLoader.LoadImage("/ui/curse.png");
        dead = ImageLoader.LoadImage("/ui/killed.png");
        meteor = ImageLoader.LoadImage("/level_1/Collectable Object/meteor.png");
        end = ImageLoader.LoadImage("/ui/end.png");
        scores = ImageLoader.LoadImage("/ui/scores.png");
        x = ImageLoader.LoadImage("/ui/x.png");
        pause = ImageLoader.LoadImage("/ui/pause.png");
        load = ImageLoader.LoadImage("/ui/load.png");


    }


}

