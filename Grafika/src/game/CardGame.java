package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import rafgfxlib.GameFrame;
import rafgfxlib.Util;
import rafgfxlib.GameFrame.GFMouseButton;



public class CardGame extends GameFrame {
	
	public static class Particle
	{
		public float posX;
		public float posY;
		public float dX;
		public float dY;
		public int life = 0;
	}
	
	private Particle[] parts = new Particle[1000];

	private BufferedImage backgroundImage = null;
	private BufferedImage backgroundRed = null;
	private Color backgroundColor = new Color(0, 200, 0);
	private BufferedImage zvezda01 = null;
	private BufferedImage thunder1 = null;
	private BufferedImage star1 = null;
	private BufferedImage heart1 = null;
	private BufferedImage think1 = null;
	private BufferedImage karo1 = null;
	private BufferedImage verzija = null;
	private BufferedImage selectedZvezda01 = null;
	private BufferedImage lightning = null;
	private BufferedImage explosion = null;
	private BufferedImage memory = null;
	private BufferedImage game = null;
	private BufferedImage newGame = null;
	private BufferedImage sng = null;
	// trenutna slika posle izmena
	private BufferedImage modImage = null;
	private BufferedImage levaIvica = null;//obrisati
	private BufferedImage desnaIvica = null;//obrisati
	private BufferedImage gornjaIvica = null;//obrisati
	private BufferedImage donjaIvica = null; //obrisati
	
	private BufferedImage pehar = null;
	
	private BufferedImage ZutaH = null;
	private BufferedImage ZutaV = null;
	private BufferedImage PlavaH = null;
	private BufferedImage PlavaV = null;
	private BufferedImage SivaH = null;
	private BufferedImage SivaV = null;
	private BufferedImage CrnaH = null;
	private BufferedImage CrnaV = null;
	
	
	private int pobednik = 0;
	
	private int pozX = 160;
	private int pozY = 200;
	private int zvezdaX = 120;
	private int zvezdaY = 180;
	private int razmakX = 80;
	private int razmakY = 80;

	private int lightningWidth = 120;
	private int lightningHeight = 2;
	private int explosionWidth = 30;
	private int explosionHeight = 2;

	private String prvaKarta = "00";
	private String drugaKarta = "00";
	private int izabranaPrvaKarta = 0;
	private int izabranaDrugaKarta = 0;
	private ArrayList<String> izabraneKarte;

	private int brojac = 0;
	private int brojac1;
	private int brojac2;
	private int brojac3;
	private int brojac4;
	private int brojac5;

	private int score = 0;
	private int p=1;
	private int brojacOkretKarte = 0;
	private int okretKarte = 1;
	private BufferedImage pokretnaZvezda = null;

	
	private int novaIgra = 0;
	private int memX;
	private int gameX;
	private int select = 0;
	public CardGame() {

		super("RAF GAME", 1240, 840);
		setHighQuality(false);
		setUpdateRate(60);
		izabraneKarte = new ArrayList<>();
		zvezda01 = Util.loadImage("/slike/zvezda1.png");
		thunder1 = Util.loadImage("/slike/thunder1.png");
		star1 = Util.loadImage("/slike/star1.png");
		heart1 = Util.loadImage("/slike/heart1.png");
		think1 = Util.loadImage("/slike/think1.png");
		karo1 = Util.loadImage("/slike/karo1.png");
		verzija = Util.loadImage("/slike/verzija.png");
		lightning = Util.loadImage("/slike/lightning.png");
		explosion = Util.loadImage("/slike/explosion.png");
		pehar = Util.loadImage("/slike/trofej.png");
		memory = Util.loadImage("/slike/g.png");
		memX = -21-memory.getWidth();
		game = Util.loadImage("/slike/m.png");
		sng = Util.loadImage("/slike/sng.png");
		gameX = this.getWidth()+70;
		newGame = Util.loadImage("/slike/novaigra.png");
		backgroundImage = generateTerrain(backgroundImage);
		backgroundRed = redEdges(backgroundImage);
		selectedZvezda01 = selected(zvezda01);
		levaIvica = Util.loadImage("/slike/zuti_okvir_strana.png");

		desnaIvica = Util.loadImage("/slike/sivi_okvir_strana.png");
		gornjaIvica = Util.loadImage("/slike/crni_gore.png");
		donjaIvica = Util.loadImage("/slike/plavi_gore.png");
		
		ZutaH = Util.loadImage("/slike/zuti_gore.png");
		ZutaV = Util.loadImage("/slike/zuti_okvir_strana.png");
		PlavaH = Util.loadImage("/slike/plavi_gore.png");
		PlavaV = Util.loadImage("/slike/plavi_okvir_strana.png");
		CrnaH = Util.loadImage("/slike/crni_gore.png");
		CrnaV = Util.loadImage("/slike/crni_okvir_strana.png");
		SivaH = Util.loadImage("/slike/sivi_gore.png");
		SivaV = Util.loadImage("/slike/sivi_okvir_strana.png");
		for(int i = 0; i < 1000; ++i)
			parts[i] = new Particle();
	//	System.out.println(pehar.getWidth()+" "+pehar.getHeight());
		
		startThread();
		
	}
	private void genEx(float cX, float cY, float radius, int life, int count)
	{
		for(Particle p : parts)
		{
			if(p.life <= 0)
			{
				p.life = (int)(Math.random() * life * 0.5) + life / 2;
				p.posX = cX;
				p.posY = cY;
				double angle = Math.random() * Math.PI * 2.0;
				double speed = Math.random() * radius;
				p.dX = (float)(Math.cos(angle) * speed);
				p.dY = (float)(Math.sin(angle) * speed);
				
				count--;
				if(count <= 0) return;
			}
		}
	}

	public static void main(String[] args) {
		GameFrame gf = new CardGame();
		gf.initGameWindow();

	}

	@Override
	public void handleWindowInit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleWindowDestroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		System.out.println("Kliknuto");
		if(novaIgra==1)
			izaberiKartu();
		if(select == 1){
			novaIgra=1;
		}
		//if(pobednik == 1)
			//genEx(x, y, 10.0f, 300, 50);
	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub

	}

	// TODO
	private boolean flag1 = false;
	private boolean flag2 = false;
	private boolean flag3 = false;
	private boolean flag4 = false;
	private boolean flag5 = false;
	private boolean flag6 = false;
	private boolean flag7 = false;
	private boolean flag8 = false;
	private boolean flag9 = false;
	private boolean flag0 = false;
	@Override
	public void handleMouseMove(int x, int y) {
		
		
		// TODO Auto-generated method stub
		
		if(novaIgra==1){
			if(x > pozX && x< (pozX+120) && y > pozY && y< pozY +180) {
				flag1 = true;
			
			} else {
				flag1 = false;
			}
			if(x > pozX+200 && x< (pozX+200+120) && y > pozY && y< pozY +180) {
				flag2 = true;
			
			} else {
				flag2 = false;
			}
			if(x > pozX+400 && x< (pozX+400+120) && y > pozY && y< pozY +180) {
				flag3 = true;
			
			} else {
				flag3 = false;
			}
			if(x > pozX+600 && x< (pozX+600+120) && y > pozY && y< pozY +180) {
				flag4 = true;
			
			} else {
				flag4 = false;
			}
			if(x > pozX+800 && x< (pozX+800+120) && y > pozY && y< pozY +180) {
				flag5 = true;
			
			} else {
				flag5 = false;
			}
			if(x > pozX && x< (pozX+120) && y > pozY+260 && y< pozY+260 +180) {
				flag6 = true;
			
			} else {
				flag6 = false;
			}
			if(x > pozX+200 && x< (pozX+200+120) && y > pozY+260 && y< pozY+260 +180) {
				flag7 = true;
			
			} else {
				flag7 = false;
			}
			if(x > pozX+400 && x< (pozX+400+120) && y > pozY+260 && y< pozY+260 +180) {
				flag8 = true;
			
			} else {
				flag8 = false;
			}
			if(x > pozX+600 && x< (pozX+600+120) && y > pozY+260 && y< pozY+260 +180) {
				flag9 = true;
			
			} else {
				flag9 = false;
			}
			if(x > pozX+800 && x< (pozX+800+120) && y > pozY+260 && y< pozY+260 +180) {
				flag0 = true;
				
			} else {
				flag0 = false;
			}
		}else{
			if(x>this.getWidth()/2-newGame.getWidth()/2 && x<this.getWidth()/2+newGame.getWidth()/2 && y>450 && y<450+newGame.getHeight())
				select =1;
			else
				select = 0;
			
			
		}
	}

	@Override
	public void handleKeyDown(int keyCode) {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleKeyUp(int keyCode) {
		// TODO Auto-generated method stub

	}
	private int newGameAlpha=0;
	// Trebalo bi da se pocisti Render/Update, da u Render bude samo grafika a u
	// Update logika
	@Override
	public void render(Graphics2D g, int sw, int sh) {
		// TODO Auto-generated method stub
		// cemu slozi ovo sranje?
		
		
		// g.clearRect(0, 0, sw, sh);
		if(novaIgra==0){
			
			g.setBackground(new Color(200, 0, 0));
			g.clearRect(0, 0, sw, sh);
			
			//g.drawImage(memory, 164, 80, null);
			g.drawImage(memory, memX, 120, null);
			g.drawImage(game, gameX, 120, null);
			//g.drawImage(game, 780, 80, null);
			
			g.drawImage(newGameIcon(bi, newGameAlpha), this.getWidth()/2-newGame.getWidth()/2, 450, null);
			if (ivice==1){
				g.drawImage(levaIvica, 40, 33, null);
				g.drawImage(desnaIvica, this.getWidth()-40-desnaIvica.getWidth(), 33, null);
				//System.out.println(levaIvica.getWidth()+" "+desnaIvica.getHeight());
				g.drawImage(gornjaIvica, 40+levaIvica.getWidth(), 33, null);
				g.drawImage(donjaIvica, 40+levaIvica.getWidth(), this.getHeight()-34-donjaIvica.getHeight(), null);
			}
			
		}else if((novaIgra==1 && pobednik==0) || tajmerPobeda<80){
			g.drawImage(backgroundImage, 0, 0, null);
			prikazKarataRender(g);
			prikazKarteRender(g);
			// Ispis verzije
			g.drawImage(verzija, 0, 810, null);
			// Ispis score-a
			g.drawString("SCORE: " + score, 1145, 810);		
		}else {
			g.setBackground(Color.BLACK);
			g.clearRect(0, 0, sw, sh);
			//g.drawImage(pehar, this.getWidth()/2-pehar.getWidth()/2, this.getHeight()-pehar.getHeight(), null);
			//g.drawImage(pehar,this.getWidth()/2-pehar.getWidth(), this.getHeight()-pehar.getHeight()*2, pehar.getWidth()*2, pehar.getHeight()*2, null);
			g.drawImage(pehar,this.getWidth()/2-pehar.getWidth(),peharY, pehar.getWidth()*2, pehar.getHeight()*2, null);
			
			g.setColor(Color.yellow);
			
			for(Particle p : parts)
			{
				if(p.life <= 0) continue;
				
				g.drawLine((int)(p.posX - p.dX), (int)(p.posY - p.dY), (int)p.posX, (int)p.posY);
			}
		}
		g.drawImage(verzija, 0, 810, null);
	}
	private BufferedImage bi;
	private int tajmer=0;
	private int ivice = 0;
	private int tajmerIvice = 0;
	private int kombinacijaIvica = 1;
	private int tajmerPobeda=0;
	private int peharY=880;
	private int tajmerSlavlje= 0;
	@Override
	public void update() {
		if(pobednik==1){
			tajmerPobeda++;
			if(tajmerPobeda>80&&peharY>this.getHeight()-pehar.getHeight()*2){
				peharY-=2;
			}
			if(tajmerPobeda>80&&peharY<=this.getHeight()-pehar.getHeight()*2){
				
				
				for(Particle p : parts)
				{
					if(p.life <= 0) continue;
					
					p.life--;
					p.posX += p.dX;
					p.posY += p.dY;
					p.dX *= 0.99f;
					p.dY *= 0.99f;
					p.dY += 0.1f;
					
					if(p.posX < 0)
					{
						p.posX = 0.01f;
						p.dX = Math.abs(p.dX) * (float)Math.random() * 0.6f;
					}
					
					if(p.posY < 0)
					{
						p.posY = 0.01f;
						p.dY = Math.abs(p.dY) * (float)Math.random() * 0.6f;
					}
					
					if(p.posX > getWidth())
					{
						p.posX = getWidth() - 0.01f;
						p.dX = Math.abs(p.dX) * (float)Math.random() * -0.6f;
					}
					
					if(p.posY > getHeight())
					{
						p.posY = getHeight() - 0.01f;
						p.dY = Math.abs(p.dY) * (float)Math.random() * -0.6f;
					}
				}
				tajmerSlavlje++;
				if(tajmerSlavlje==60){
					genEx(100, 100, 10.0f, 300, 50);
					genEx(308, 130, 10.0f, 300, 50);
					genEx(516, 80, 10.0f, 300, 50);
					genEx(724, 150, 10.0f, 300, 50);
					genEx(832, 100, 10.0f, 300, 50);
					genEx(1040, 60, 10.0f, 300, 50);
					tajmerSlavlje=0;
				}
			}
		
		}
		if(select==0)
			bi = newGameIcon(newGame, newGameAlpha);
		else
			bi = newGameIcon(sng, newGameAlpha);
		if (memX!=164){
			memX+=2;
		}
		if(gameX!=780){
			gameX-=2;
		}
		if(gameX == 780 && memX==164){
			ivice = 1;
			tajmerIvice++;
			tajmerIvice = tajmerIvice%31;
			if(tajmerIvice==0){
				switch (kombinacijaIvica) {
				case 0:
					gornjaIvica = CrnaH;
					levaIvica = ZutaV;
					donjaIvica = PlavaH;
					desnaIvica = SivaV;
					
					break;
				case 1:
					gornjaIvica = SivaH;
					levaIvica = CrnaV;
					donjaIvica = ZutaH;
					desnaIvica = PlavaV;
					break;
				case 2:
					gornjaIvica = PlavaH;
					levaIvica = SivaV;
					donjaIvica = CrnaH;
					desnaIvica = ZutaV;
					break;
				case 3:
					gornjaIvica = ZutaH;
					levaIvica = PlavaV;
					donjaIvica = SivaH;
					desnaIvica = CrnaV;
					break;

				default:
					break;
				}
				kombinacijaIvica = (kombinacijaIvica+1)%4;
				
			}
			
			
		}
		if(novaIgra==1){
			prikazKarataUpdate();
			prikazKarteUpdate();
		}
		
		if (izabraneKarte.size() == 10 && pobednik == 0) {
			System.out.println("Kraj");
			pobednik = 1;
		}
		if(tajmer<65)
			tajmer++;
		if(newGameAlpha!=255 && tajmer==65){
			newGameAlpha++;
		}
		updateScore();

	}

	public BufferedImage redEdges(BufferedImage original) {

		WritableRaster modRaster = Util.createRaster(original.getWidth(), original.getHeight(), false);
		WritableRaster origRaster = original.getRaster();
		int[] srcRGB = new int[4];
		int[] modRGB = new int[4];
		modRGB[0] = 0;
		modRGB[1] = 0;
		modRGB[2] = 0;
		modRGB[3] = 180;
		float alpha = (float) modRGB[3] / 255;
		int origW = origRaster.getWidth();
		int origH = origRaster.getHeight();
		int inkrement = 255;
		for (int y = 0; y < 256; y++) {
			modRGB[0] = inkrement--;
			for (int x = 0; x < origW; x++) {
				origRaster.getPixel(x, y, srcRGB);
				srcRGB[0] = Util.clamp((int) (modRGB[0] + (1.0f - alpha) * srcRGB[0]), 0, 255);
				srcRGB[1] = Util.clamp((int) (modRGB[1] + (1.0f - alpha) * srcRGB[1]), 0, 255);
				srcRGB[2] = Util.clamp((int) (modRGB[2] + (1.0f - alpha) * srcRGB[2]), 0, 255);

				modRaster.setPixel(x, y, srcRGB);
			}
		}
		for (int y = origH - (256+400); y < origH-400; y++) {
			modRGB[0] = inkrement++;
			for (int x = 0; x < origW; x++) {
				origRaster.getPixel(x, y, srcRGB);

				srcRGB[0] = Util.clamp((int) (modRGB[0] + (1.0f - alpha) * srcRGB[0]), 0, 255);
				srcRGB[1] = Util.clamp((int) (modRGB[1] + (1.0f - alpha) * srcRGB[1]), 0, 255);
				srcRGB[2] = Util.clamp((int) (modRGB[2] + (1.0f - alpha) * srcRGB[2]), 0, 255);

				modRaster.setPixel(x, y, srcRGB);
			}
		}

		for (int y = 0; y < origH; y++) {
			inkrement = 255;
			for (int x = 0; x < 256; x++) {
				origRaster.getPixel(x, y, srcRGB);
				modRGB[0] = inkrement--;
				srcRGB[0] = Util.clamp((int) (modRGB[0] + (1.0f - alpha) * srcRGB[0]), 0, 255);
				srcRGB[1] = Util.clamp((int) (modRGB[1] + (1.0f - alpha) * srcRGB[1]), 0, 255);
				srcRGB[2] = Util.clamp((int) (modRGB[2] + (1.0f - alpha) * srcRGB[2]), 0, 255);

				modRaster.setPixel(x, y, srcRGB);
			}
		}
		for (int y = 0; y < origH; y++) {
			inkrement = 0;
			for (int x = origW - 256; x < origW; x++) {
				origRaster.getPixel(x, y, srcRGB);
				modRGB[0] = inkrement++;
				srcRGB[0] = Util.clamp((int) (modRGB[0] + (1.0f - alpha) * srcRGB[0]), 0, 255);
				srcRGB[1] = Util.clamp((int) (modRGB[1] + (1.0f - alpha) * srcRGB[1]), 0, 255);
				srcRGB[2] = Util.clamp((int) (modRGB[2] + (1.0f - alpha) * srcRGB[2]), 0, 255);

				modRaster.setPixel(x, y, srcRGB);
			}
		}
		inkrement = 0;
		modRGB[0] = 0;
		modRGB[3] = 0;
		for (int y = 255; y < origH - (256+400); y++) {

			for (int x = 256; x < origW - 256; x++) {
				origRaster.getPixel(x, y, srcRGB);

				srcRGB[0] = Util.clamp((int) (modRGB[0] + (1.0f - alpha) * srcRGB[0]), 0, 255);
				srcRGB[1] = Util.clamp((int) (modRGB[1] + (1.0f - alpha) * srcRGB[1]), 0, 255);
				srcRGB[2] = Util.clamp((int) (modRGB[2] + (1.0f - alpha) * srcRGB[2]), 0, 255);

				modRaster.setPixel(x, y, srcRGB);
			}
		}

		return Util.rasterToImage(modRaster);

	}

	public BufferedImage generateTerrain(BufferedImage original) {
		int octaves = 4;

		int octaveSize = 6;

		float persistence = 0.65f;

		int width = (int) Math.pow(octaveSize, octaves);
		int height = width;

		WritableRaster target = Util.createRaster(width, height, false);

		float[][] tempMap = new float[width][height];

		float[][] finalMap = new float[width][height];

		float multiplier = 1.0f;

		for (int o = 0; o < octaves; ++o) {
			float[][] octaveMap = new float[octaveSize][octaveSize];

			for (int x = 0; x < octaveSize; ++x) {
				for (int y = 0; y < octaveSize; ++y) {
					octaveMap[x][y] = ((float) Math.random() - 0.5f) * 2.0f;
				}
			}

			Util.floatMapRescaleCos(octaveMap, tempMap);

			Util.floatMapMAD(tempMap, finalMap, multiplier);
			octaveSize *= 2;

			multiplier *= persistence;
		}

		// .................
		Random r = new Random();
		// 7 boja
		int[] poceciIntervala = new int[5];
		poceciIntervala[0] = r.nextInt(540) + 300;
		poceciIntervala[1] = r.nextInt(540) + 300;
		poceciIntervala[2] = r.nextInt(540) + 300;
		poceciIntervala[3] = r.nextInt(540) + 300;
		poceciIntervala[4] = r.nextInt(540) + 300;

		Arrays.sort(poceciIntervala);
		WritableRaster raster = Util.createRaster(1240, 1240, false);
		BufferedImage imgGradient = Util.loadImage("/slike/gradientMa.png");
		int[][] gradient = Util.imageToGradient(imgGradient);

		int rgb[] = new int[3];
		for (int y = 0; y < raster.getHeight(); y++) {
			for (int x = 0; x < raster.getWidth(); x++) {
				double sineY = Math.cos(x * 0.005) * poceciIntervala[0] / 10 + poceciIntervala[0];
				double sineY1 = Math.cos(x * 0.005) * poceciIntervala[1] / 4 + poceciIntervala[1];
				double sineY2 = Math.cos(x * 0.005) * poceciIntervala[2] / 6 + poceciIntervala[2];
				double sineY3 = Math.cos(x * 0.005) * poceciIntervala[3] / 8 + poceciIntervala[3];
				double sineY4 = Math.cos(x * 0.005) * poceciIntervala[4] / 10 + poceciIntervala[4];
				if (sineY > y) {
					rgb[0] = 0;
					rgb[1] = 102;
					rgb[2] = 204;
					float grad = (finalMap[x][y]);
					Util.gradientSample(gradient, grad, rgb);
				} else if (sineY1 > y) {
					rgb[0] = 0;
					rgb[1] = 255;
					rgb[2] = 0;
				} else if (sineY2 > y) {
					rgb[0] = 153;
					rgb[1] = 76;
					rgb[2] = 0;
				} else if (sineY3 > y) {
					rgb[0] = 60;
					rgb[1] = 30;
					rgb[2] = 0;
				} else if (sineY4 > y) {
					rgb[0] = 80;
					rgb[1] = 80;
					rgb[2] = 80;
				}
				raster.setPixel(x, y, rgb);
			}
		}
		original = Util.rasterToImage(raster);
		return original;
	}

	public BufferedImage selected(BufferedImage original) {
		WritableRaster modRaster = Util.createRaster(original.getWidth(), original.getHeight(), false);
		WritableRaster origRaster = original.getRaster();
		// int posX = (origRaster.getWidth() - modRaster.getWidth()) / 2;
		// int posY = (origRaster.getHeight() - modRaster.getHeight()) / 2;
		int[] srcRGB = new int[4];
		int[] modRGB = new int[4];
		modRGB[0] = 0;
		modRGB[1] = 0;
		modRGB[2] = 255;
		modRGB[3] = 80;
		float alpha = (float) modRGB[3] / 255;
		int origW = origRaster.getWidth();
		int origH = origRaster.getHeight();
		for (int y = 0; y < origH; y++) {

			for (int x = 0; x < origW; x++) {
				origRaster.getPixel(x, y, srcRGB);

				srcRGB[0] = Util.clamp((int) (modRGB[0] + (1.0f - alpha) * srcRGB[0]), 0, 255);
				srcRGB[1] = Util.clamp((int) (modRGB[1] + (1.0f - alpha) * srcRGB[1]), 0, 255);
				srcRGB[2] = Util.clamp((int) (modRGB[2] + (1.0f - alpha) * srcRGB[2]), 0, 255);

				modRaster.setPixel(x, y, srcRGB);
			}
		}
		return Util.rasterToImage(modRaster);
	}

	public BufferedImage bilinearResize(BufferedImage img, int targetWidth, int targetHeight) {
		// proveravam da li slika ima alpha kanal ili nema (da li je transparentna ili
		// ne)
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = img;
		BufferedImage modImage = null;
		Graphics2D g2 = null;

		int w = img.getWidth();
		int h = img.getHeight();

		int prevW = w;
		int prevH = h;

		do {
			if (w > targetWidth) {
				w /= 2;
				w = (w < targetWidth) ? targetWidth : w;
			}

			if (h > targetHeight) {
				h /= 2;
				h = (h < targetHeight) ? targetHeight : h;
			}
			// pravim g2 jer pomocu njega crtam u BufferedImage
			if (modImage == null) {
				modImage = new BufferedImage(w, h, type);
				g2 = modImage.createGraphics();
			}

			g2.drawImage(ret, 0, 0, w, h, 0, 0, prevW, prevH, null);

			prevW = w;
			prevH = h;
			ret = modImage;
		} while (w != targetWidth || h != targetHeight);

		if (g2 != null) {
			// zatvaram stari g2 da ne bih imao jednu preko druge vise slika
			// razlicitih velicina
			g2.dispose();
		}

		if (targetWidth != ret.getWidth() || targetHeight != ret.getHeight()) {
			modImage = new BufferedImage(targetWidth, targetHeight, type);
			g2 = modImage.createGraphics();
			g2.drawImage(ret, 0, 0, null);
			g2.dispose();
			ret = modImage;
		}

		return ret;

	}

	public BufferedImage blend(BufferedImage original, BufferedImage mod) {

		WritableRaster source = original.getRaster();
		WritableRaster over = mod.getRaster();

		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), false);

		// B - background, F - foreground
		int rgbB[] = new int[4];
		int rgbF[] = new int[4];
		int rgb[] = new int[4];

		for (int y = 0; y < source.getHeight(); y++) {
			for (int x = 0; x < source.getWidth(); x++) {
				source.getPixel(x, y, rgbB);
				if(!(x>= over.getWidth()) && !(y>= over.getHeight())) {
				over.getPixel(x, y, rgbF);
				float alpha = rgbF[3] / 255.0f;
				// Ovaj put boje mnozimo, ali u skaliranom 0-1 opsegu, kako bi
				// i rezultat bio broj od 0 do 1, sto onda skaliramo nazad na 0-255.
				rgb[0] = (int) (((rgbB[0] * (1- alpha)) + (rgbF[0] * (alpha))));
				rgb[1] = (int) (((rgbB[1] * (1- alpha)) + (rgbF[1] * (alpha))));
				rgb[2] = (int) (((rgbB[2] * (1- alpha)) + (rgbF[2] * (alpha))));
				} else {
					rgb[0] = rgbB[0];
					rgb[1] = rgbB[1];
					rgb[2] = rgbB[2];
				}
				target.setPixel(x, y, rgb);
			}
		}
		return Util.rasterToImage(target); 
	}

	public void prikazKarataRender(Graphics2D g) {
		// Proverava da li su u listi izabraneKarte nase 2 karte i ako nisu crta back a
		// ako jesu crta front na kratko
		// Povezani par nestane nakon delay od 1.5 sec
		if (!izabraneKarte.contains("01") && !izabraneKarte.contains("11")) {
			if(flag1) {
				g.drawImage(selectedZvezda01, pozX, pozY, null);
			} else {
				g.drawImage(zvezda01, pozX, pozY, null);
			}		
			if(flag6) {
				g.drawImage(selectedZvezda01, pozX, pozY + 260, null);
			} else {
				g.drawImage(zvezda01, pozX, pozY + 260, null);
			}
		} else {
			if (brojac1 < 90) {
				//g.drawImage(think1, pozX, pozY, null);
				g.drawImage(blend(think1, bilinearResize(explosion, explosionWidth, explosionHeight)), pozX, pozY , null);
				//g.drawImage(think1, pozX, pozY + 260, null);
				g.drawImage(blend(think1, bilinearResize(explosion, explosionWidth, explosionHeight)), pozX, pozY + 260, null);
				} else {
			}
		}
		if (!izabraneKarte.contains("02") && !izabraneKarte.contains("12")) {
			if(flag2) {
				g.drawImage(selectedZvezda01, pozX+200, pozY, null);
			} else {
				g.drawImage(zvezda01, pozX + 200, pozY, null);
			}
			
			if(flag7) {
				g.drawImage(selectedZvezda01, pozX+200, pozY + 260, null);
			} else {
				g.drawImage(zvezda01, pozX + 200, pozY + 260, null);
			}
		} else {
			if (brojac2 < 90) {
				g.drawImage(backgroundRed, 0, 0, null);
				g.drawImage(thunder1, pozX + 200, pozY, null);
				g.drawImage(thunder1, pozX + 200, pozY + 260, null);
			}
		}
		if (!izabraneKarte.contains("03") && !izabraneKarte.contains("13")) {
			if(flag3) {
				g.drawImage(selectedZvezda01, pozX+400, pozY, null);
			} else {
				g.drawImage(zvezda01, pozX + 400, pozY, null);
			}
			if(flag8) {
				g.drawImage(selectedZvezda01, pozX+400, pozY + 260, null);
			} else {
				g.drawImage(zvezda01, pozX + 400, pozY + 260, null);
			}
		} else {
			if (brojac3 < 90) {
				g.drawImage(star1, pozX + 400, pozY, null);
				g.drawImage(bilinearResize(lightning, lightningWidth, lightningHeight), pozX + 400, pozY, null);
				g.drawImage(star1, pozX + 400, pozY + 260, null);
				g.drawImage(bilinearResize(lightning, lightningWidth, lightningHeight), pozX + 400, pozY+260, null);
			}
		}
		if (!izabraneKarte.contains("04") && !izabraneKarte.contains("14")) {
			if(flag4) {
				g.drawImage(selectedZvezda01, pozX+600, pozY, null);
			} else {
				g.drawImage(zvezda01, pozX + 600, pozY, null);
			}
			if(flag9) {
				g.drawImage(selectedZvezda01, pozX+600, pozY + 260, null);
			} else {
				g.drawImage(zvezda01, pozX + 600, pozY + 260, null);
			}
		} else {
			if (brojac4 < 90) {
				g.drawImage(heart1, pozX + 600, pozY, null);
				g.drawImage(heart1, pozX + 600, pozY + 260, null);
			}
		}
		if (!izabraneKarte.contains("05") && !izabraneKarte.contains("15")) {
			if(flag5) {
				g.drawImage(selectedZvezda01, pozX+800, pozY, null);
			} else {
				g.drawImage(zvezda01, pozX + 800, pozY, null);
			}
			
			if(flag0) {
				g.drawImage(selectedZvezda01, pozX+800, pozY + 260, null);
			} else {
				g.drawImage(zvezda01, pozX + 800, pozY + 260, null);
			}
		} else {
			if (brojac5 < 90) {
				g.drawImage(karo1, pozX + 800, pozY, null);
				g.drawImage(karo1, pozX + 800, pozY + 260, null);
			}
		}
	}
	public void prikazKarataUpdate() {
		if (!izabraneKarte.contains("01") && !izabraneKarte.contains("11")) {
			brojac1 = 0;
		} else {
			if (brojac1 < 90) {
				brojac1++;
				
				explosionHeight += 2;
				explosionWidth += 1;
			} else {
				
				explosionWidth = 30;
				explosionHeight = 2;
			}
		}
		if (!izabraneKarte.contains("02") && !izabraneKarte.contains("12")) {
			brojac2 = 0;
		} else {
			if (brojac2 < 90) {
				brojac2++;
			}
		}
		if (!izabraneKarte.contains("03") && !izabraneKarte.contains("13")) {
			brojac3 = 0;
		} else {
			if (brojac3 < 90) {
				brojac3++;
				lightningHeight += 2;
			} else {
				lightningHeight = 2;
			}
		}
		if (!izabraneKarte.contains("04") && !izabraneKarte.contains("14")) {
			brojac4 = 0;
		} else {
			if (brojac4 < 90) {
				brojac4++;
			}
		}
		if (!izabraneKarte.contains("05") && !izabraneKarte.contains("15")) {
			brojac5 = 0;
		} else {
			if (brojac5 < 90) {
				brojac5++;
			}
		}

	}

	public void prikazKarteRender(Graphics2D g) {
		// Ispis prvih trenutno kliknutih karata
		// Ispisuje trenutno kliknutu prvu kartu, druga ostaje sakrivena(?)
		// Ispis trenutno kliknutih karata u prvom redu
		if (prvaKarta == "01" && drugaKarta != "11") {
			if (brojac < 90) {
				g.drawImage(think1, pozX, pozY, null);
			}
			if(okretKarte==1) {
				g.drawImage(pokretnaZvezda, pozX, pozY, null);	
			}
		}
		if (prvaKarta == "02" && drugaKarta != "12") {
			if (brojac < 90) {
				g.drawImage(thunder1, pozX + 200, pozY, null);
			}
			if(okretKarte==1) {
				g.drawImage(pokretnaZvezda, pozX+200, pozY, null);	
			}
		}
		if (prvaKarta == "03" && drugaKarta != "13") {
			if (brojac < 90) {
				g.drawImage(star1, pozX + 400, pozY, null);
			}
			if(okretKarte==1) {
				g.drawImage(pokretnaZvezda, pozX+400, pozY, null);	
			}
		}
		if (prvaKarta == "04" && drugaKarta != "14") {
			if (brojac < 90) {
				g.drawImage(heart1, pozX + 600, pozY, null);
			}
			if(okretKarte==1) {
				g.drawImage(pokretnaZvezda, pozX+600, pozY, null);	
			}
		}
		if (prvaKarta == "05" && drugaKarta != "15") {
			if (brojac < 90) {
				g.drawImage(karo1, pozX + 800, pozY, null);
			}
			if(okretKarte==1) {
				g.drawImage(pokretnaZvezda, pozX+800, pozY, null);	
			}
		}
		// Ispis trenutno kliknutih karata u drugom redu
		if (drugaKarta == "11" && prvaKarta != "01") {
			if (brojac < 90) {
				g.drawImage(think1, pozX, pozY + 260, null);
			}
			if(okretKarte==1) {
				g.drawImage(pokretnaZvezda, pozX, pozY+260, null);	
			}
		}
		if (drugaKarta == "12" && prvaKarta != "02") {
			if (brojac < 90) {
				g.drawImage(thunder1, pozX + 200, pozY + 260, null);
			}
			if(okretKarte==1) {
				g.drawImage(pokretnaZvezda, pozX+200, pozY+260, null);	
			}
		}
		if (drugaKarta == "13" && prvaKarta != "03") {
			if (brojac < 90) {
				g.drawImage(star1, pozX + 400, pozY + 260, null);
			}
			if(okretKarte==1) {
				g.drawImage(pokretnaZvezda, pozX+400, pozY+260, null);	
			}
		}
		if (drugaKarta == "14" && prvaKarta != "04") {
			if (brojac < 90) {
				g.drawImage(heart1, pozX + 600, pozY + 260, null);
			}
			if(okretKarte==1) {
				g.drawImage(pokretnaZvezda, pozX+600, pozY+260, null);	
			}
		}
		if (drugaKarta == "15" && prvaKarta != "05") {
			if (brojac < 90) {
				g.drawImage(karo1, pozX + 800, pozY + 260, null);
			}
			if(okretKarte==1) {
				g.drawImage(pokretnaZvezda, pozX+800, pozY+260, null);	
			}
		}

	}
	public void okretanjeKarte() {
		pokretnaZvezda= Util.loadImage("/slike/zvezda"+p+".png");
	//	if(isMouseButtonDown(GFMouseButton.Left))
		//	okretKarte=1;
		if(okretKarte==1&&brojacOkretKarte>=0) {
			if(brojacOkretKarte==3) {
				p=2;
			}if(brojacOkretKarte==6) {
				p=3;
			}if(brojacOkretKarte==9) {
				p=4;
			}if(brojacOkretKarte==12) {
				p=5;
			}if(brojacOkretKarte==15) {
				p=6;
			}if(brojacOkretKarte==18) {
				p=7;
			}
			brojacOkretKarte++;
			if(brojacOkretKarte==30) {
				brojacOkretKarte=0;
				okretKarte=0;
				p=1;
			}
		}
	}
	public void prikazKarteUpdate() {
		if (izabraneKarte.size() > 1) {
			String kartaPrva = izabraneKarte.get(0).substring(1, 1);

			if (!izabraneKarte.get(1).endsWith(kartaPrva) && brojac < 90) {
				// TODO Kako iscrtati karte koje su izabrane?
				brojac++;
			}
			brojac = 0;
		}
		// Ispis prvih trenutno kliknutih karata
		// Ispisuje trenutno kliknutu prvu kartu, druga ostaje sakrivena(?)
		// Ispis trenutno kliknutih karata u prvom redu
		if (prvaKarta == "01" && drugaKarta != "11") {
			okretanjeKarte();
			if (brojac < 90) {
				brojac++;
			}
			brojac = 0;
		}
		if (prvaKarta == "02" && drugaKarta != "12") {
			okretanjeKarte();
			if (brojac < 90) {
				brojac++;
			}
			brojac = 0;
		}
		if (prvaKarta == "03" && drugaKarta != "13") {
			okretanjeKarte();
			if (brojac < 90) {
				brojac++;
			}
			brojac = 0;
		}
		if (prvaKarta == "04" && drugaKarta != "14") {
			okretanjeKarte();
			if (brojac < 90) {
				brojac++;
			}		
			brojac = 0;
		}
		if (prvaKarta == "05" && drugaKarta != "15") {
			okretanjeKarte();
			if (brojac < 90) {
				brojac++;
			}
			brojac = 0;
		}
		// Ispis trenutno kliknutih karata u drugom redu
		if (drugaKarta == "11" && prvaKarta != "01") {
			okretanjeKarte();
			if (brojac < 90) {
				brojac++;
			}
			brojac = 0;
		}
		if (drugaKarta == "12" && prvaKarta != "02") {
			okretanjeKarte();
			if (brojac < 90) {
				brojac++;
			}
			brojac = 0;
		}
		if (drugaKarta == "13" && prvaKarta != "03") {
			okretanjeKarte();
			if (brojac < 90) {
				brojac++;
			}
			brojac = 0;
		}
		if (drugaKarta == "14" && prvaKarta != "04") {
			okretanjeKarte();
			if (brojac < 90) {
				brojac++;
			}
			brojac = 0;
		}
		if (drugaKarta == "15" && prvaKarta != "05") {
			okretanjeKarte();
			if (brojac < 90) {
				brojac++;
			}
			brojac = 0;
		}

	}

	private void izaberiKartu() {
		izaberiDruguKartu();
		izaberiPrvuKartu();
		izabraneObeKarte();

	}

	private void izaberiPrvuKartu() {
		// Namesta da je prva karta jedna od mogucih pozicija
		if (izabranaPrvaKarta == 0) {
			if (isMouseButtonDown(GFMouseButton.Left)) {
				if (kojaKarta(getMouseX(), getMouseY()) != null && kojaKarta(getMouseX(), getMouseY()) != "00") {
					if (!izabraneKarte.contains(kojaKarta(getMouseX(), getMouseY()))) {
						prvaKarta = kojaKarta(getMouseX(), getMouseY());
						izabranaPrvaKarta = 1;
						izabranaDrugaKarta = 0;
						okretKarte=1;
					} else {
						System.out.println("Zeljena karta je vec izabrana");
					}
				}
			}
		}
	}

	private void izaberiDruguKartu() {
		// Namesta da je druga karta jedna od mogucih pozicija
		if (izabranaDrugaKarta == 0) {
			if (isMouseButtonDown(GFMouseButton.Left)) {
				if (kojaKarta(getMouseX(), getMouseY()) != null && kojaKarta(getMouseX(), getMouseY()) != "00") {
					if (prvaKarta != kojaKarta(getMouseX(), getMouseY())) {
						if (!izabraneKarte.contains(kojaKarta(getMouseX(), getMouseY()))) {
							drugaKarta = kojaKarta(getMouseX(), getMouseY());
							izabranaDrugaKarta = 1;
							okretKarte=1;
						} else {
							System.out.println("Zeljena karta je vec izabrana");
						}
					}
				}
			}
		}
	}

	private boolean isteKarte() {
		// Vraca koje karte su trenutni parovi, zbog nebitnosti gameplay/price u nasoj
		// "igri" mislim da parovi
		// mogu da budu svaka gornja i donja karta
		if ((prvaKarta == "01" && drugaKarta == "11") || (drugaKarta == "01" && prvaKarta == "11"))
			return true;
		if ((prvaKarta == "02" && drugaKarta == "12") || (drugaKarta == "02" && prvaKarta == "12"))
			return true;
		if ((prvaKarta == "03" && drugaKarta == "13") || (drugaKarta == "03" && prvaKarta == "13"))
			return true;
		if ((prvaKarta == "04" && drugaKarta == "14") || (drugaKarta == "04" && prvaKarta == "14"))
			return true;
		if ((prvaKarta == "05" && drugaKarta == "15") || (drugaKarta == "05" && prvaKarta == "15"))
			return true;

		return false;
	}

	private void izabraneObeKarte() {
		// Proverava da li su izabrane prva i druga karta i ukoliko jesu ubaci ih u
		// listu izabraneKarte
		if (izabranaPrvaKarta == 1 && izabranaDrugaKarta == 1) {
			System.out.println("Pozicija prve karte: " + prvaKarta + '\n' + "Pozicija druge karte: " + drugaKarta);
			if (((prvaKarta != "00" && prvaKarta != null) && (drugaKarta != null && drugaKarta != "00"))
					&& (isteKarte())) {
				izabraneKarte.add(prvaKarta);
				izabraneKarte.add(drugaKarta);
			}
			prvaKarta = "00";
			drugaKarta = "00";
			izabranaPrvaKarta = 0;
			izabranaDrugaKarta = 0;
		}
	}

	private String kojaKarta(int x, int y) {
		// Vraca trenutnu poziciju karte na kojoj se nalazi mis (gornji red pocinje sa 0
		// i ide do 5, donji sa 1 i ide do 5)
		String trenutnaKarta = "00";
		// System.out.println("x = " + x + " y = " + y);
		if ((y >= pozY && y <= pozY + zvezdaY) && (x >= pozX && x <= pozX + zvezdaX))
			trenutnaKarta = "01";
		if ((y >= pozY && y <= pozY + zvezdaY)
				&& (x >= pozX + zvezdaX + razmakX && x <= pozX + zvezdaX + (zvezdaX + razmakX)))
			trenutnaKarta = "02";
		if ((y >= pozY && y <= pozY + zvezdaY)
				&& (x >= pozX + (zvezdaX + razmakX) * 2 && x <= pozX + zvezdaX + (zvezdaX + razmakX) * 2))
			trenutnaKarta = "03";
		if ((y >= pozY && y <= pozY + zvezdaY)
				&& (x >= pozX + (zvezdaX + razmakX) * 3 && x <= pozX + zvezdaX + (zvezdaX + razmakX) * 3))
			trenutnaKarta = "04";
		if ((y >= pozY && y <= pozY + zvezdaY)
				&& (x >= pozX + (zvezdaX + razmakX) * 4 && x <= pozX + zvezdaX + (zvezdaX + razmakX) * 4))
			trenutnaKarta = "05";
		if ((y >= pozY + zvezdaY + razmakY && y <= pozY + zvezdaY * 2 + razmakY) && (x >= pozX && x <= pozX + zvezdaX))
			trenutnaKarta = "11"; // 11
		if ((y >= pozY + zvezdaY + razmakY && y <= pozY + zvezdaY * 2 + razmakY)
				&& (x >= pozX + zvezdaX + razmakX && x <= pozX + zvezdaX + (zvezdaX + razmakX)))
			trenutnaKarta = "12"; // 12
		if ((y >= pozY + zvezdaY + razmakY && y <= pozY + zvezdaY * 2 + razmakY)
				&& (x >= pozX + (zvezdaX + razmakX) * 2 && x <= pozX + zvezdaX + (zvezdaX + razmakX) * 2))
			trenutnaKarta = "13"; // 13
		if ((y >= pozY + zvezdaY + razmakY && y <= pozY + zvezdaY * 2 + razmakY)
				&& (x >= pozX + (zvezdaX + razmakX) * 3 && x <= pozX + zvezdaX + (zvezdaX + razmakX) * 3))
			trenutnaKarta = "14"; // 14
		if ((y >= pozY + zvezdaY + razmakY && y <= pozY + zvezdaY * 2 + razmakY)
				&& (x >= pozX + (zvezdaX + razmakX) * 4 && x <= pozX + zvezdaX + (zvezdaX + razmakX) * 4))
			trenutnaKarta = "15"; // 15
		// pozX = 160;
		// pozY = 200;
		// zvezdaX = 120;
		// zvezdaY = 180;
		// pomeraj = 80
		// System.out.println(trenutnaKarta);
		return trenutnaKarta;

	}
	
	public BufferedImage newGameIcon(BufferedImage newGame,int x){
		WritableRaster modRaster = Util.createRaster(newGame.getWidth(), newGame.getHeight(), true);
		WritableRaster origRaster = newGame.getRaster();
		int boje []= new int [4]; 
		for(int i = 0; i<origRaster.getWidth();i++){
			for(int j = 0;j<origRaster.getHeight();j++){
				origRaster.getPixel(i, j, boje);
				boje[3]=x;
				modRaster.setPixel(i, j, boje);
			}
		}
		return Util.rasterToImage(modRaster);
	}

	private void updateScore() {
		// Dodaje poene
		if (brojac1 == 90) {
			score = score + 100;
			brojac1++;
		}
		if (brojac2 == 90) {
			score = score + 100;
			brojac2++;
		}
		if (brojac3 == 90) {
			score = score + 100;
			brojac3++;
		}
		if (brojac4 == 90) {
			score = score + 100;
			brojac4++;
		}
		if (brojac5 == 90) {
			score = score + 100;
			brojac5++;
		}
	}

}
