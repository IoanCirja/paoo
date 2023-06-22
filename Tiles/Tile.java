package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile
{
    private static final int NO_TILES   = 300;
    public static Tile[] tiles          = new Tile[NO_TILES];

    public static Tile bonus = new BonusTile(11);
    public static Tile brick_1 = new Brick1Tile(12);
    public static Tile cage = new CageTile(16);
    public static Tile chains = new ChainsTile(17);
    public static Tile column = new ColumnTile(18);
    public static Tile fence = new FenceTile(20);
    public static Tile ground_2 = new Ground2Tile(5);
    public static Tile ground_4 = new Ground4Tile(4);
    public static Tile ground_6 = new Ground6Tile(9);
    public static Tile ground_8 = new Ground8Tile(6);
    public static Tile ground_9 = new Ground9Tile(8);
    public static Tile ground_10 = new Ground10Tile(1);
    public static Tile ground_11 = new Ground11Tile(2);
    public static Tile ground_12 = new Ground12Tile(3);
    public static Tile ground_13 = new Ground13Tile(10);
    public static Tile ground_ladder = new GroundLadderTile(7);
    public static Tile ladder = new LadderTile(14);
    public static Tile lantern = new LanternTile(22);
    public static Tile rock = new RockTile(21);
    public static Tile skulls = new SkullsTile(19);
    public static Tile sign_1 = new Sign1Tile(23);
    public static Tile sign_2 = new Sign2Tile(24);
    public static Tile sign_3 = new Sign3Tile(25);
    public static Tile sign_4 = new Sign4Tile(26);
    public static Tile sign_5 = new Sign5Tile(27);
    public static Tile sign_6 = new Sign6Tile(28);
    public static Tile spikes = new SpikesTile(29);
    public static Tile statue = new StatueTile(30);
    public static Tile wooden_barrel = new WoodenBarrelTile(13);
    public static Tile wooden_box = new WoodenBoxTile(15);
    public static Tile invisible = new Invisible(0);



    public static Tile lvl2_bonus = new Level2_BonusTile(111);
    public static Tile lvl2_brick_1 = new Level2_Brick1Tile(112);
    public static Tile lvl2_cage = new Level2_CageTile(116);
    public static Tile lvl2_chains = new Level2_ChainsTile(117);
    public static Tile lvl2_column = new Level2_ColumnTile(118);
    public static Tile lvl2_fence = new Level2_FenceTile(120);
    public static Tile lvl2_ground_2 = new Level2_Ground2Tile(105);
    public static Tile lvl2_ground_4 = new Level2_Ground4Tile(104);
    public static Tile lvl2_ground_6 = new Level2_Ground6Tile(109);
    public static Tile lvl2_ground_8 = new Level2_Ground8Tile(106);
    public static Tile lvl2_ground_9 = new Level2_Ground9Tile(108);
    public static Tile lvl2_ground_10 = new Level2_Ground10Tile(101);
    public static Tile lvl2_ground_11 = new Level2_Ground11Tile(102);
    public static Tile lvl2_ground_12 = new Level2_Ground12Tile(103);
    public static Tile lvl2_ground_13 = new Level2_Ground13Tile(110);
    public static Tile lvl2_ground_ladder = new Level2_GroundLadderTile(107);
    public static Tile lvl2_ladder = new Level2_LadderTile(114);
    public static Tile lvl2_lantern = new Level2_LanternTile(122);
    public static Tile lvl2_rock = new Level2_RockTile(121);
    public static Tile lvl2_skulls = new Level2_SkullsTile(119);
    public static Tile lvl2_sign_1 = new Level2_Sign1Tile(123);
    public static Tile lvl2_sign_2 = new Level2_Sign2Tile(124);
    public static Tile lvl2_sign_3 = new Level2_Sign3Tile(125);
    public static Tile lvl2_sign_4 = new Level2_Sign4Tile(126);
    public static Tile lvl2_sign_5 = new Level2_Sign5Tile(127);
    public static Tile lvl2_sign_6 = new Level2_Sign6Tile(128);
    public static Tile lvl2_spikes = new Level2_SpikesTile(129);
    public static Tile lvl2_statue = new Level2_StatueTile(130);
    public static Tile lvl2_wooden_barrel = new Level2_WoodenBarrelTile(113);
    public static Tile lvl2_wooden_box = new Level2_WoodenBoxTile(115);



    public static Tile lvl3_bonus = new Level3_BonusTile(211);
    public static Tile lvl3_brick_1 = new Level3_Brick1Tile(212);
    public static Tile lvl3_cage = new Level3_CageTile(216);
    public static Tile lvl3_chains = new Level3_ChainsTile(217);
    public static Tile lvl3_column = new Level3_ColumnTile(218);
    public static Tile lvl3_fence = new Level3_FenceTile(220);
    public static Tile lvl3_ground_2 = new Level3_Ground2Tile(205);
    public static Tile lvl3_ground_4 = new Level3_Ground4Tile(204);
    public static Tile lvl3_ground_6 = new Level3_Ground6Tile(209);
    public static Tile lvl3_ground_8 = new Level3_Ground8Tile(206);
    public static Tile lvl3_ground_9 = new Level3_Ground9Tile(208);
    public static Tile lvl3_ground_10 = new Level3_Ground10Tile(201);
    public static Tile lvl3_ground_11 = new Level3_Ground11Tile(202);
    public static Tile lvl3_ground_12 = new Level3_Ground12Tile(203);
    public static Tile lvl3_ground_13 = new Level3_Ground13Tile(210);
    public static Tile lvl3_ground_ladder = new Level3_GroundLadderTile(207);
    public static Tile lvl3_ladder = new Level3_LadderTile(214);
    public static Tile lvl3_lantern = new Level3_LanternTile(222);
    public static Tile lvl3_rock = new Level3_RockTile(221);
    public static Tile lvl3_skulls = new Level3_SkullsTile(219);
    public static Tile lvl3_sign_1 = new Level3_Sign1Tile(223);
    public static Tile lvl3_sign_2 = new Level3_Sign2Tile(224);
    public static Tile lvl3_sign_3 = new Level3_Sign3Tile(225);
    public static Tile lvl3_sign_4 = new Level3_Sign4Tile(226);
    public static Tile lvl3_sign_5 = new Level3_Sign5Tile(227);
    public static Tile lvl3_sign_6 = new Level3_Sign6Tile(228);
    public static Tile lvl3_spikes = new Level3_SpikesTile(229);
    public static Tile lvl3_statue = new Level3_StatueTile(230);
    public static Tile lvl3_wooden_barrel = new Level3_WoodenBarrelTile(215);
    public static Tile lvl3_wooden_box = new Level3_WoodenBoxTile(213);




















































    public static final int TILE_WIDTH  = 64;
    public static final int TILE_HEIGHT = 64;

    protected BufferedImage img;
    protected final int id;


    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    public void Update()
    {

    }


    public void Draw(Graphics g, int x, int y)
    {

        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }


    public boolean IsSolid()
    {
        return false;
    }


    public int GetId()
    {
        return id;
    }





}
