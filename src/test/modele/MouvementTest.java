package test.modele;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import javafx.beans.property.SimpleStringProperty;
import modele.Mouvement;

public class MouvementTest
{
	Mouvement mTourB, mTourN, mReineN, mReineB, mFouN, mRoiB, mRoiN;

	@Before
	public void testMouvement()
	{
		mTourB = new Mouvement(new SimpleStringProperty("R"), new SimpleStringProperty("nouvelEmplacement"),
				new SimpleStringProperty("/8/8/8/8/8/8/8/8"));
		mTourN = new Mouvement(new SimpleStringProperty("r"), new SimpleStringProperty("nouvelEmplacement"),
				new SimpleStringProperty("/8/8/8/8/8/8/8/8"));
		mReineN = new Mouvement(new SimpleStringProperty("q"), new SimpleStringProperty("nouvelEmplacement"),
				new SimpleStringProperty("/8/8/8/8/8/8/8/8"));
		mReineB = new Mouvement(new SimpleStringProperty("Q"), new SimpleStringProperty("nouvelEmplacement"),
				new SimpleStringProperty("/8/8/8/8/8/8/8/8"));
		mFouN = new Mouvement(new SimpleStringProperty("b"), new SimpleStringProperty("nouvelEmplacement"),
				new SimpleStringProperty("/8/8/8/8/8/8/8/8"));
		mRoiB = new Mouvement(new SimpleStringProperty("K"), new SimpleStringProperty("nouvelEmplacement"),
				new SimpleStringProperty("/8/8/8/8/8/8/8/8"));
		mRoiN = new Mouvement(new SimpleStringProperty("k"), new SimpleStringProperty("nouvelEmplacement"),
				new SimpleStringProperty("/8/8/8/8/8/8/8/8"));

	}

	@Test
	public void testGetNomCompletDeLaPiece()
	{
		assertEquals("tour ", mTourB.getNomCompletDeLaPiece());
		assertEquals("tour ", mTourN.getNomCompletDeLaPiece());
		assertEquals("reine ", mReineN.getNomCompletDeLaPiece());
		assertEquals("reine ", mReineB.getNomCompletDeLaPiece());
		assertEquals("fou ", mFouN.getNomCompletDeLaPiece());
	}

	@Test
	public void testGetNouvelleEmplacement()
	{
		assertEquals("nouvelEmplacement", mTourB.getNouvelleEmplacement().get());
		assertEquals("nouvelEmplacement", mTourN.getNouvelleEmplacement().get());
		assertEquals("nouvelEmplacement", mReineN.getNouvelleEmplacement().get());
		assertEquals("nouvelEmplacement", mReineB.getNouvelleEmplacement().get());
		assertEquals("nouvelEmplacement", mFouN.getNouvelleEmplacement().get());
	}

	@Test
	public void testToString()
	{
		assertEquals("La tour blanche se déplace sur nouvelEmplacement.", mTourB.toString());
		assertEquals("La tour noire se déplace sur nouvelEmplacement.", mTourN.toString());
		assertEquals("La reine noire se déplace sur nouvelEmplacement.", mReineN.toString());
		assertEquals("La reine blanche se déplace sur nouvelEmplacement.", mReineB.toString());
		assertEquals("Le fou noir se déplace sur nouvelEmplacement.", mFouN.toString());
	}

	@Test
	public void testToStringCastle()
	{
		assertEquals("Le roi blanc castle.", mRoiB.toStringCastle());
		assertEquals("Le roi noir castle.", mRoiN.toStringCastle());
	}

	@Test
	public void testGetFen()
	{
		assertEquals("/8/8/8/8/8/8/8/8", mTourB.getFen().get());
		assertEquals("/8/8/8/8/8/8/8/8", mTourN.getFen().get());
		assertEquals("/8/8/8/8/8/8/8/8", mReineN.getFen().get());
		assertEquals("/8/8/8/8/8/8/8/8", mReineB.getFen().get());
		assertEquals("/8/8/8/8/8/8/8/8", mFouN.getFen().get());
	}

}
