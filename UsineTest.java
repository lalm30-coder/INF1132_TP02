package inf2120.s242.tp2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsineTest {
    @Test
    void testMinePlacer() {
        Usine usine = new Usine( 3, 4 );
        Mine mine = new Mine( IdentiteProduit.ACANTHITE, 1, 1 );

        assertDoesNotThrow( () -> mine.placer( 1, 1, usine ) );
        assertEquals( mine, usine.stations[ 1 ][ 1 ] );
    }

    @Test
    void testMinePlacer2() {
        Usine usine = new Usine( 3, 4 );
        Mine mine = new Mine( IdentiteProduit.ACANTHITE, 1, 1 );

        assertDoesNotThrow( () -> mine.placer( 1, 1, usine ) );
        assertEquals( mine, usine.stations[ 1 ][ 1 ] );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 1, 1 ) );
    }

    @Test
    void testMinePlacer3() {
        Usine usine = new Usine( 3, 4 );
        usine.logistique.setTapis( 1, 1, TapisRoulant.DROITE_BAS );
        Mine mine = new Mine( IdentiteProduit.ACANTHITE, 1, 1 );

        assertThrows( PlacementIncorrectException.class, () -> mine.placer( 1, 1, usine ) );
        assertNull( usine.stations[ 1 ][ 1 ] );
        assertEquals( TapisRoulant.DROITE_BAS, usine.logistique.getTapis( 1, 1 ) );
    }

    @Test
    void testMineAcanthiteTic() {
        Usine usine = new Usine( 3, 4 );
        Mine mine = new Mine( IdentiteProduit.ACANTHITE, 1, 1 );
        mine.placer( 1, 1, usine );
        usine.logistique.setTapis( 2, 1, TapisRoulant.GAUCHE_DROITE );

        mine.tic( usine );
        assertNull( usine.trouverItem( 2, 1 ) );
        mine.tic( usine );
        assertEquals( IdentiteProduit.ACANTHITE, usine.trouverItem( 2, 1 ).identite );
    }

    @Test
    void testMineCassiteriteTic() {
        Usine usine = new Usine( 3, 4 );
        Mine mine = new Mine( IdentiteProduit.CASSITERITE, 1, 2 );
        mine.placer( 1, 2, usine );
        usine.logistique.setTapis( 2, 2, TapisRoulant.GAUCHE_DROITE );

        for( int i = 0; i < 6; ++ i ) {
            mine.tic( usine );
            assertNull( usine.trouverItem( 2, 2 ) );
        }
        mine.tic( usine );
        assertEquals( IdentiteProduit.CASSITERITE, usine.trouverItem( 2, 2 ).identite );
    }

    @Test
    void testMineChalcociteTic() {
        Usine usine = new Usine( 3, 4 );
        Mine mine = new Mine( IdentiteProduit.CHALCOCITE, 1, 2 );
        mine.placer( 1, 2, usine );
        usine.logistique.setTapis( 2, 2, TapisRoulant.GAUCHE_DROITE );

        for( int i = 0; i < 2; ++ i ) {
            mine.tic( usine );
            assertNull( usine.trouverItem( 2, 2 ) );
        }
        mine.tic( usine );
        assertEquals( IdentiteProduit.CHALCOCITE, usine.trouverItem( 2, 2 ).identite );
    }

    @Test
    void testMineCharbonTic() {
        Usine usine = new Usine( 3, 4 );
        Mine mine = new Mine( IdentiteProduit.CHARBON, 1, 2 );
        mine.placer( 1, 2, usine );
        usine.logistique.setTapis( 2, 2, TapisRoulant.GAUCHE_DROITE );

        mine.tic( usine );
        assertEquals( IdentiteProduit.CHARBON, usine.trouverItem( 2, 2 ).identite );
    }

    @Test
    void testMoulinPlacer1() {
        Usine usine = new Usine( 4, 4 );
        Moulin moulin = new Moulin(1, 1 );

        assertDoesNotThrow( () -> moulin.placer( 1, 1, usine ) );
        assertEquals( moulin, usine.stations[ 1 ][ 1 ] );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 1, 1 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 2, 1 ) );
    }

    @Test
    void testMoulinPlacer2() {
        Usine usine = new Usine( 3, 4 );
        usine.logistique.setTapis( 1, 1, TapisRoulant.DROITE_BAS );
        Moulin moulin = new Moulin(1, 1 );

        assertThrows( PlacementIncorrectException.class, () -> moulin.placer( 1, 1, usine ) );
        assertNull( usine.stations[ 1 ][ 1 ] );
        assertNull( usine.stations[ 1 ][ 2 ] );
        assertEquals( TapisRoulant.DROITE_BAS, usine.logistique.getTapis( 1, 1 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 1 ) );
    }

    @Test
    void testMoulinPlacer3() {
        Usine usine = new Usine( 3, 4 );
        usine.logistique.setTapis( 2, 1, TapisRoulant.DROITE_BAS );
        Moulin moulin = new Moulin(1, 1 );

        assertThrows( PlacementIncorrectException.class, () -> moulin.placer( 1, 1, usine ) );
        assertNull( usine.stations[ 1 ][ 1 ] );
        assertNull( usine.stations[ 1 ][ 2 ] );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 1, 1 ) );
        assertEquals( TapisRoulant.DROITE_BAS, usine.logistique.getTapis( 2, 1 ) );
    }

    @Test
    void testMoulinTic1() {
        Usine usine = new Usine( 5, 4 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.BAS_HAUT );
        Moulin moulin = new Moulin(1, 1 );
        moulin.placer( 1, 1, usine );
        usine.logistique.setTapis( 3, 1, TapisRoulant.HAUT_BAS );

        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.ACANTHITE ) );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertFalse( usine.logistique.contiensItem( 3, 1 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 3, 1 ) );
        usine.tic();
        assertEquals( IdentiteProduit.POUDRE_ACANTHITE, usine.logistique.trouverItem( 3, 1 ).identite );
        usine.logistique.extraireItem( 3, 1 );
        for( int i = 0; i < 3; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 1 ) );
        }
    }

    @Test
    void testMoulinTic2() {
        Usine usine = new Usine( 5, 4 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.BAS_HAUT );
        Moulin moulin = new Moulin(1, 1 );
        moulin.placer( 1, 1, usine );
        usine.logistique.setTapis( 3, 1, TapisRoulant.HAUT_BAS );

        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.CASSITERITE ) );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertFalse( usine.logistique.contiensItem( 3, 1 ) );

        for( int i = 0; i < 5; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 1 ) );
        }

        usine.tic();
        assertEquals( IdentiteProduit.POUDRE_CASSITERITE, usine.logistique.trouverItem( 3, 1 ).identite );
        usine.logistique.extraireItem( 3, 1 );
        for( int i = 0; i < 3; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 1 ) );
        }
    }

    @Test
    void testMoulinTic3() {
        Usine usine = new Usine( 5, 4 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.BAS_HAUT );
        Moulin moulin = new Moulin(1, 1 );
        moulin.placer( 1, 1, usine );
        usine.logistique.setTapis( 3, 1, TapisRoulant.HAUT_BAS );

        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.CHALCOCITE ) );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertFalse( usine.logistique.contiensItem( 3, 1 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 3, 1 ) );
        usine.tic();
        assertEquals( IdentiteProduit.POUDRE_CHALCOCITE, usine.logistique.trouverItem( 3, 1 ).identite );
        usine.logistique.extraireItem( 3, 1 );
        for( int i = 0; i < 3; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 1 ) );
        }
    }

    @Test
    void testMoulinTic4() {
        Usine usine = new Usine( 5, 4 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.BAS_HAUT );
        Moulin moulin = new Moulin(1, 1 );
        moulin.placer( 1, 1, usine );
        usine.logistique.setTapis( 3, 1, TapisRoulant.HAUT_BAS );

        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.CHALCOCITE ) );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertFalse( usine.logistique.contiensItem( 3, 1 ) );
        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.CHALCOCITE ) );
        usine.tic();
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        assertFalse( usine.logistique.contiensItem( 3, 1 ) );
        usine.tic();
        assertEquals( IdentiteProduit.POUDRE_CHALCOCITE, usine.logistique.trouverItem( 3, 1 ).identite );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        usine.logistique.extraireItem( 3, 1 );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertFalse( usine.logistique.contiensItem( 3, 1 ) );
    }

    @Test
    void testFournaisePlacer1() {
        Usine usine = new Usine( 6, 6 );
        Fournaise fournaise = new Fournaise(2, 2 );

        assertDoesNotThrow( () -> fournaise.placer( 2, 2, usine ) );
        assertEquals( fournaise, usine.stations[ 2 ][ 2 ] );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 2, 2 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 2, 1 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 2, 3 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 3, 2 ) );
    }

    @Test
    void testFournaisePlacer2() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 2, 2, TapisRoulant.DROITE_GAUCHE );
        Fournaise fournaise = new Fournaise(2, 2 );

        assertThrows( PlacementIncorrectException.class, () -> fournaise.placer( 2, 2, usine ) );
        assertNull( usine.stations[ 2 ][ 2 ] );
        assertNull( usine.stations[ 1 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 2 ] );
        assertNull( usine.stations[ 2 ][ 3 ] );
        assertEquals( TapisRoulant.DROITE_GAUCHE, usine.logistique.getTapis( 2, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 1 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 2 ) );
    }

    @Test
    void testFournaisePlacer3() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 2, 1, TapisRoulant.DROITE_GAUCHE );
        Fournaise fournaise = new Fournaise(2, 2 );

        assertThrows( PlacementIncorrectException.class, () -> fournaise.placer( 2, 2, usine ) );
        assertNull( usine.stations[ 2 ][ 2 ] );
        assertNull( usine.stations[ 1 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 2 ] );
        assertNull( usine.stations[ 2 ][ 3 ] );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 3 ) );
        assertEquals( TapisRoulant.DROITE_GAUCHE, usine.logistique.getTapis( 2, 1 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 2 ) );
    }

    @Test
    void testFournaisePlacer4() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 2, 3, TapisRoulant.DROITE_GAUCHE );
        Fournaise fournaise = new Fournaise(2, 2 );

        assertThrows( PlacementIncorrectException.class, () -> fournaise.placer( 2, 2, usine ) );
        assertNull( usine.stations[ 2 ][ 2 ] );
        assertNull( usine.stations[ 1 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 2 ] );
        assertNull( usine.stations[ 2 ][ 3 ] );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 2 ) );
        assertEquals( TapisRoulant.DROITE_GAUCHE, usine.logistique.getTapis( 2, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 1 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 2 ) );
    }

    @Test
    void testFournaisePlacer5() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 3, 2, TapisRoulant.DROITE_GAUCHE );
        Fournaise fournaise = new Fournaise(2, 2 );

        assertThrows( PlacementIncorrectException.class, () -> fournaise.placer( 2, 2, usine ) );
        assertNull( usine.stations[ 2 ][ 2 ] );
        assertNull( usine.stations[ 1 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 2 ] );
        assertNull( usine.stations[ 2 ][ 3 ] );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 1 ) );
        assertEquals( TapisRoulant.DROITE_GAUCHE, usine.logistique.getTapis( 3, 2 ) );
    }

    @Test
    void testFournaiseTic1() {
        Usine usine = new Usine( 5, 5 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.BAS_HAUT );
        usine.logistique.setTapis( 0, 3, TapisRoulant.BAS_HAUT );
        Fournaise fournaise = new Fournaise(1, 2 );
        fournaise.placer( 1, 2, usine );
        usine.logistique.setTapis( 3, 2, TapisRoulant.HAUT_BAS );

        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.ACANTHITE ) );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        for( int i = 0; i < 2; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
        usine.tic();
        assertEquals( IdentiteProduit.LITHARGE, usine.logistique.trouverItem( 3, 2 ).identite );
        usine.logistique.extraireItem( 3, 2 );
        for( int i = 0; i < 4; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
    }

    @Test
    void testFournaiseTic2() {
        Usine usine = new Usine( 5, 5 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.BAS_HAUT );
        usine.logistique.setTapis( 0, 3, TapisRoulant.BAS_HAUT );
        Fournaise fournaise = new Fournaise(1, 2 );
        fournaise.placer( 1, 2, usine );
        usine.logistique.setTapis( 3, 2, TapisRoulant.HAUT_BAS );

        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.POUDRE_ACANTHITE ) );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        for( int i = 0; i < 2; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
        usine.tic();
        assertEquals( IdentiteProduit.LITHARGE, usine.logistique.trouverItem( 3, 2 ).identite );
        usine.logistique.extraireItem( 3, 2 );
        for( int i = 0; i < 4; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
    }

    @Test
    void testFournaiseTic3() {
        Usine usine = new Usine( 5, 5 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.BAS_HAUT );
        usine.logistique.setTapis( 0, 3, TapisRoulant.BAS_HAUT );
        Fournaise fournaise = new Fournaise(1, 2 );
        fournaise.placer( 1, 2, usine );
        usine.logistique.setTapis( 3, 2, TapisRoulant.HAUT_BAS );

        for( int i = 0; i < 7; ++ i ) {
            usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.LINGOT_CUIVRE ) );
            assertTrue( usine.logistique.contiensItem( 0, 1 ) );
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 0, 1 ) );
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
        usine.logistique.placerItem( 0, 3, new Produit( IdentiteProduit.LINGOT_ETAIN ) );
        assertTrue( usine.logistique.contiensItem( 0, 3 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 3 ) );
        assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        for( int i = 0; i < 2; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
        usine.tic();
        assertEquals( IdentiteProduit.LINGOT_BRONZE, usine.logistique.trouverItem( 3, 2 ).identite );
        usine.logistique.extraireItem( 3, 2 );
        for( int i = 0; i < 4; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
    }

    @Test
    void testFournaiseTic4() {
        Usine usine = new Usine( 5, 5 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.BAS_HAUT );
        usine.logistique.setTapis( 0, 3, TapisRoulant.BAS_HAUT );
        Fournaise fournaise = new Fournaise(1, 2 );
        fournaise.placer( 1, 2, usine );
        usine.logistique.setTapis( 3, 2, TapisRoulant.HAUT_BAS );

        for( int i = 0; i < 3; ++ i ) {
            usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.LINGOT_CUIVRE ) );
            assertTrue( usine.logistique.contiensItem( 0, 1 ) );
            usine.tic();
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 0, 1 ) );
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
        usine.logistique.placerItem( 0, 3, new Produit( IdentiteProduit.LINGOT_ETAIN ) );
        assertTrue( usine.logistique.contiensItem( 0, 3 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 3 ) );
        assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        for( int i = 0; i < 4; ++ i ) {
            usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.LINGOT_CUIVRE ) );
            assertTrue( usine.logistique.contiensItem( 0, 1 ) );
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 0, 1 ) );
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
        for( int i = 0; i < 2; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
        usine.tic();
        assertEquals( IdentiteProduit.LINGOT_BRONZE, usine.logistique.trouverItem( 3, 2 ).identite );
        usine.logistique.extraireItem( 3, 2 );
        for( int i = 0; i < 4; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
    }

    @Test
    void testFournaiseDeGrillagePlacer1() {
        Usine usine = new Usine( 6, 6 );
        FournaiseDeGrillage fournaise = new FournaiseDeGrillage(2, 3 );

        assertDoesNotThrow( () -> fournaise.placer( 2, 3, usine ) );
        assertEquals( fournaise, usine.stations[ 3 ][ 2 ] );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 2, 3 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 1, 3 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 2, 2 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 3, 3 ) );
    }

    @Test
    void testFournaiseDeGrillagePlacer2() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 2, 3, TapisRoulant.DROITE_GAUCHE );
        FournaiseDeGrillage fournaise = new FournaiseDeGrillage(2, 3 );

        assertThrows( PlacementIncorrectException.class, () -> fournaise.placer( 2, 3, usine ) );
        assertNull( usine.stations[ 3 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 1 ] );
        assertNull( usine.stations[ 2 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 3 ] );
        assertEquals( TapisRoulant.DROITE_GAUCHE, usine.logistique.getTapis( 2, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 1, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 3 ) );
    }

    @Test
    void testFournaiseDeGrillagePlacer3() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 1, 3, TapisRoulant.DROITE_GAUCHE );
        FournaiseDeGrillage fournaise = new FournaiseDeGrillage(2, 3 );

        assertThrows( PlacementIncorrectException.class, () -> fournaise.placer( 2, 3, usine ) );
        assertNull( usine.stations[ 3 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 1 ] );
        assertNull( usine.stations[ 2 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 3 ] );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 3 ) );
        assertEquals( TapisRoulant.DROITE_GAUCHE, usine.logistique.getTapis( 1, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 3 ) );
    }

    @Test
    void testFournaiseDeGrillagePlacer4() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 2, 2, TapisRoulant.DROITE_GAUCHE );
        FournaiseDeGrillage fournaise = new FournaiseDeGrillage(2, 3 );

        assertThrows( PlacementIncorrectException.class, () -> fournaise.placer( 2, 3, usine ) );
        assertNull( usine.stations[ 3 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 1 ] );
        assertNull( usine.stations[ 2 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 3 ] );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 1, 3 ) );
        assertEquals( TapisRoulant.DROITE_GAUCHE, usine.logistique.getTapis( 2, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 3 ) );
    }

    @Test
    void testFournaiseDeGrillagePlacer5() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 3, 3, TapisRoulant.DROITE_GAUCHE );
        FournaiseDeGrillage fournaise = new FournaiseDeGrillage(2, 3 );

        assertThrows( PlacementIncorrectException.class, () -> fournaise.placer( 2, 3, usine ) );
        assertNull( usine.stations[ 3 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 1 ] );
        assertNull( usine.stations[ 2 ][ 2 ] );
        assertNull( usine.stations[ 3 ][ 3 ] );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 1, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 2, 2 ) );
        assertEquals( TapisRoulant.DROITE_GAUCHE, usine.logistique.getTapis( 3, 3 ) );
    }

    @Test
    void testFournaiseDeGrillageTic1() {
        Usine usine = new Usine( 8, 4 );
        usine.logistique.setTapis( 1, 2, TapisRoulant.GAUCHE_DROITE );
        usine.logistique.setTapis( 3, 0, TapisRoulant.BAS_HAUT );
        FournaiseDeGrillage fournaise = new FournaiseDeGrillage(3, 2 );
        fournaise.placer( 3, 2, usine );
        usine.logistique.setTapis( 5, 2, TapisRoulant.HAUT_DROITE );

        usine.logistique.placerItem( 1, 2, new Produit( IdentiteProduit.LITHARGE ) );
        assertTrue( usine.logistique.contiensItem( 1, 2 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 1, 2 ) );
        assertFalse( usine.logistique.contiensItem( 5, 2 ) );
        usine.logistique.placerItem( 3, 0, new Produit( IdentiteProduit.COKE ) );
        assertTrue( usine.logistique.contiensItem( 3, 0 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 3, 0 ) );
        assertFalse( usine.logistique.contiensItem( 5, 2 ) );
        for( int i = 0; i < 12; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 5, 2 ) );
        }
        usine.tic();
        assertEquals( IdentiteProduit.OXYDE_ARGENT, usine.logistique.trouverItem( 5, 2 ).identite );
        usine.logistique.extraireItem( 5, 2 );
        for( int i = 0; i < 20; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 5, 2 ) );
        }
    }

    @Test
    void testFournaiseDeGrillageTic2() {
        Usine usine = new Usine( 8, 4 );
        usine.logistique.setTapis( 1, 2, TapisRoulant.GAUCHE_DROITE );
        usine.logistique.setTapis( 3, 0, TapisRoulant.BAS_HAUT );
        FournaiseDeGrillage fournaise = new FournaiseDeGrillage(3, 2 );
        fournaise.placer( 3, 2, usine );
        usine.logistique.setTapis( 5, 2, TapisRoulant.HAUT_DROITE );

        usine.logistique.placerItem( 1, 2, new Produit( IdentiteProduit.CASSITERITE ) );
        assertTrue( usine.logistique.contiensItem( 1, 2 ) );
        usine.logistique.placerItem( 3, 0, new Produit( IdentiteProduit.COKE ) );
        assertTrue( usine.logistique.contiensItem( 3, 0 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 1, 2 ) );
        assertFalse( usine.logistique.contiensItem( 3, 0 ) );
        assertFalse( usine.logistique.contiensItem( 5, 2 ) );
        for( int i = 0; i < 10; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 5, 2 ) );
        }
        usine.tic();
        assertEquals( IdentiteProduit.OXYDE_ETAIN, usine.logistique.trouverItem( 5, 2 ).identite );
        usine.logistique.extraireItem( 5, 2 );
        for( int i = 0; i < 20; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 5, 2 ) );
        }
    }

    @Test
    void testFournaiseDeGrillageTic3() {
        Usine usine = new Usine( 8, 4 );
        usine.logistique.setTapis( 1, 2, TapisRoulant.GAUCHE_DROITE );
        usine.logistique.setTapis( 3, 0, TapisRoulant.BAS_HAUT );
        FournaiseDeGrillage fournaise = new FournaiseDeGrillage(3, 2 );
        fournaise.placer( 3, 2, usine );
        usine.logistique.setTapis( 5, 2, TapisRoulant.HAUT_DROITE );

        usine.logistique.placerItem( 3, 0, new Produit( IdentiteProduit.COKE ) );
        assertTrue( usine.logistique.contiensItem( 3, 0 ) );
        usine.tic();
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 3, 0 ) );
        assertFalse( usine.logistique.contiensItem( 5, 2 ) );
        usine.logistique.placerItem( 1, 2, new Produit( IdentiteProduit.POUDRE_CASSITERITE ) );
        assertTrue( usine.logistique.contiensItem( 1, 2 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 1, 2 ) );
        assertFalse( usine.logistique.contiensItem( 5, 2 ) );
        for( int i = 0; i < 10; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 5, 2 ) );
        }
        usine.tic();
        assertEquals( IdentiteProduit.OXYDE_ETAIN, usine.logistique.trouverItem( 5, 2 ).identite );
        usine.logistique.extraireItem( 5, 2 );
        for( int i = 0; i < 20; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 5, 2 ) );
        }
    }

    @Test
    void testFournaiseDeCoupellationPlacer1() {
        Usine usine = new Usine( 4, 4 );
        FournaiseDeCoupellation fournaise = new FournaiseDeCoupellation(1, 1 );

        assertDoesNotThrow( () -> fournaise.placer( 1, 1, usine ) );
        assertEquals( fournaise, usine.stations[ 1 ][ 1 ] );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 1, 1 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 1, 0 ) );
    }

    @Test
    void testFournaiseDeCoupellationPlacer2() {
        Usine usine = new Usine( 4, 4 );
        usine.logistique.setTapis( 1, 1, TapisRoulant.HAUT_BAS );
        FournaiseDeCoupellation fournaise = new FournaiseDeCoupellation(1, 1 );

        assertThrows( PlacementIncorrectException.class, () -> fournaise.placer( 1, 1, usine ) );
        assertNull( usine.stations[ 1 ][ 1 ] );
        assertNull( usine.stations[ 0 ][ 1 ] );
        assertEquals( TapisRoulant.HAUT_BAS, usine.logistique.getTapis( 1, 1 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 1, 0 ) );
    }

    @Test
    void testFournaiseDeCoupellationPlacer3() {
        Usine usine = new Usine( 4, 4 );
        usine.logistique.setTapis( 1, 0, TapisRoulant.HAUT_BAS );
        FournaiseDeCoupellation fournaise = new FournaiseDeCoupellation(1, 1 );

        assertThrows( PlacementIncorrectException.class, () -> fournaise.placer( 1, 1, usine ) );
        assertNull( usine.stations[ 1 ][ 1 ] );
        assertNull( usine.stations[ 0 ][ 1 ] );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 1, 1 ) );
        assertEquals( TapisRoulant.HAUT_BAS, usine.logistique.getTapis( 1, 0 ) );
    }

    @Test
    void testFournaiseDeCoupellationTic1() {
        Usine usine = new Usine( 4, 8 );
        usine.logistique.setTapis( 1, 4, TapisRoulant.BAS_DROITE );
        FournaiseDeCoupellation fournaise = new FournaiseDeCoupellation(2, 5 );
        fournaise.placer( 2, 5, usine );
        usine.logistique.setTapis( 3, 5, TapisRoulant.GAUCHE_BAS );

        usine.logistique.placerItem( 1, 4, new Produit( IdentiteProduit.OXYDE_ARGENT ) );
        assertTrue( usine.logistique.contiensItem( 1, 4 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 1, 4 ) );
        assertFalse( usine.logistique.contiensItem( 3, 5 ) );
        for( int i = 0; i < 8; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 5 ) );
        }
        usine.tic();
        assertEquals( IdentiteProduit.LINGOT_ARGENT, usine.logistique.trouverItem( 3, 5 ).identite );
        usine.logistique.extraireItem( 3, 5 );
        for( int i = 0; i < 10; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 5 ) );
        }
    }

    @Test
    void testFournaiseDeCoupellationTic2() {
        Usine usine = new Usine( 4, 8 );
        usine.logistique.setTapis( 1, 4, TapisRoulant.BAS_DROITE );
        FournaiseDeCoupellation fournaise = new FournaiseDeCoupellation(2, 5 );
        fournaise.placer( 2, 5, usine );
        usine.logistique.setTapis( 3, 5, TapisRoulant.GAUCHE_BAS );

        usine.logistique.placerItem( 1, 4, new Produit( IdentiteProduit.OXYDE_ETAIN ) );
        assertTrue( usine.logistique.contiensItem( 1, 4 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 1, 4 ) );
        assertFalse( usine.logistique.contiensItem( 3, 5 ) );
        usine.tic();
        assertEquals( IdentiteProduit.LINGOT_ETAIN, usine.logistique.trouverItem( 3, 5 ).identite );
        usine.logistique.extraireItem( 3, 5 );
        for( int i = 0; i < 10; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 5 ) );
        }
    }

    @Test
    void testFournaiseDeCoupellationTic3() {
        Usine usine = new Usine( 4, 8 );
        usine.logistique.setTapis( 1, 4, TapisRoulant.BAS_DROITE );
        FournaiseDeCoupellation fournaise = new FournaiseDeCoupellation(2, 5 );
        fournaise.placer( 2, 5, usine );
        usine.logistique.setTapis( 3, 5, TapisRoulant.GAUCHE_BAS );

        usine.logistique.placerItem( 1, 4, new Produit( IdentiteProduit.OXYDE_CUIVRE ) );
        assertTrue( usine.logistique.contiensItem( 1, 4 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 1, 4 ) );
        assertFalse( usine.logistique.contiensItem( 3, 5 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 3, 5 ) );
        usine.tic();
        assertEquals( IdentiteProduit.LINGOT_CUIVRE, usine.logistique.trouverItem( 3, 5 ).identite );
        usine.logistique.extraireItem( 3, 5 );
        for( int i = 0; i < 10; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 5 ) );
        }
    }

    @Test
    void testTouraillePlacer1() {
        Usine usine = new Usine( 6, 6 );
        Touraille touraille = new Touraille(3, 2 );

        assertDoesNotThrow( () -> touraille.placer( 3, 2, usine ) );
        assertEquals( touraille, usine.stations[ 2 ][ 3 ] );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 3, 2 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 4, 2 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 3, 3 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 4, 3 ) );
    }

    @Test
    void testTouraillePlacer2() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 3, 2, TapisRoulant.OCCUPE );
        Touraille touraille = new Touraille(3, 2 );

        assertThrows( PlacementIncorrectException.class, () -> touraille.placer( 3, 2, usine ) );
        assertNull( usine.stations[ 2 ][ 3 ] );
        assertNull( usine.stations[ 2 ][ 4 ] );
        assertNull( usine.stations[ 3 ][ 3 ] );
        assertNull( usine.stations[ 3 ][ 4 ] );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 3, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 4, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 4, 3 ) );
    }

    @Test
    void testTouraillePlacer3() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 4, 2, TapisRoulant.OCCUPE );
        Touraille touraille = new Touraille(3, 2 );

        assertThrows( PlacementIncorrectException.class, () -> touraille.placer( 3, 2, usine ) );
        assertNull( usine.stations[ 2 ][ 3 ] );
        assertNull( usine.stations[ 2 ][ 4 ] );
        assertNull( usine.stations[ 3 ][ 3 ] );
        assertNull( usine.stations[ 3 ][ 4 ] );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 2 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 4, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 4, 3 ) );
    }

    @Test
    void testTouraillePlacer4() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 3, 3, TapisRoulant.OCCUPE );
        Touraille touraille = new Touraille(3, 2 );

        assertThrows( PlacementIncorrectException.class, () -> touraille.placer( 3, 2, usine ) );
        assertNull( usine.stations[ 2 ][ 3 ] );
        assertNull( usine.stations[ 2 ][ 4 ] );
        assertNull( usine.stations[ 3 ][ 3 ] );
        assertNull( usine.stations[ 3 ][ 4 ] );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 4, 2 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 3, 3 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 4, 3 ) );
    }

    @Test
    void testTouraillePlacer5() {
        Usine usine = new Usine( 6, 6 );
        usine.logistique.setTapis( 4, 3, TapisRoulant.OCCUPE );
        Touraille touraille = new Touraille(3, 2 );

        assertThrows( PlacementIncorrectException.class, () -> touraille.placer( 3, 2, usine ) );
        assertNull( usine.stations[ 2 ][ 3 ] );
        assertNull( usine.stations[ 2 ][ 4 ] );
        assertNull( usine.stations[ 3 ][ 3 ] );
        assertNull( usine.stations[ 3 ][ 4 ] );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 4, 2 ) );
        assertEquals( TapisRoulant.VIDE, usine.logistique.getTapis( 3, 3 ) );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 4, 3 ) );
    }

    @Test
    void testTourailleTic1() {
        Usine usine = new Usine( 4, 4 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.BAS_DROITE );
        Touraille touraille = new Touraille(1, 1 );
        touraille.placer( 1, 1, usine );
        usine.logistique.setTapis( 3, 2, TapisRoulant.GAUCHE_BAS );

        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.CHARBON ) );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        for( int i = 0; i < 18; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
        usine.tic();
        assertEquals( IdentiteProduit.COKE, usine.logistique.trouverItem( 3, 2 ).identite );
        usine.logistique.extraireItem( 3, 2 );
        for( int i = 0; i < 40; ++ i ) {
            usine.tic();
            assertFalse( usine.logistique.contiensItem( 3, 2 ) );
        }
    }

    @Test
    void testVendeurPlacer1() {
        Usine usine = new Usine( 3, 3 );
        Vendeur vendeur = new Vendeur( 1, 1 );

        assertDoesNotThrow( () -> vendeur.placer( 1, 1, usine ) );
        assertEquals( vendeur, usine.stations[ 1 ][ 1 ] );
        assertEquals( TapisRoulant.OCCUPE, usine.logistique.getTapis( 1, 1 ) );
    }

    @Test
    void testVendeurPlacer2() {
        Usine usine = new Usine( 3, 3 );
        usine.logistique.setTapis( 1, 1, TapisRoulant.DROITE_BAS );
        Vendeur vendeur = new Vendeur( 1, 1 );

        assertThrows( PlacementIncorrectException.class, () -> vendeur.placer( 1, 1, usine ) );
        assertNull( usine.stations[ 1 ][ 1 ] );
        assertEquals( TapisRoulant.DROITE_BAS, usine.logistique.getTapis( 1, 1 ) );
    }

    @Test
    void testVendeurTic1() {
        Usine usine = new Usine( 3, 3 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.DROITE_BAS );
        Vendeur vendeur = new Vendeur( 1, 1 );
        vendeur.placer( 1, 1, usine );

        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.CHARBON ) );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 0, usine.getVente( IdentiteProduit.CHARBON ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 1, usine.getVente( IdentiteProduit.CHARBON ) );
    }

    @Test
    void testVendeurTic2() {
        Usine usine = new Usine( 3, 3 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.DROITE_BAS );
        Vendeur vendeur = new Vendeur( 1, 1 );
        vendeur.placer( 1, 1, usine );

        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.CHARBON ) );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 0, usine.getVente( IdentiteProduit.CHARBON ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 1, usine.getVente( IdentiteProduit.CHARBON ) );
        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.CHARBON ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 2, usine.getVente( IdentiteProduit.CHARBON ) );
        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.CHARBON ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 3, usine.getVente( IdentiteProduit.CHARBON ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 3, usine.getVente( IdentiteProduit.CHARBON ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 3, usine.getVente( IdentiteProduit.CHARBON ) );
    }

    @Test
    void testVendeurTic3() {
        Usine usine = new Usine( 3, 3 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.DROITE_BAS );
        Vendeur vendeur = new Vendeur( 1, 1 );
        vendeur.placer( 1, 1, usine );

        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.OXYDE_CUIVRE ) );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 0, usine.getVente( IdentiteProduit.OXYDE_CUIVRE ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 1, usine.getVente( IdentiteProduit.OXYDE_CUIVRE ) );
    }

    @Test
    void testVendeurTic4() {
        Usine usine = new Usine( 3, 3 );
        usine.logistique.setTapis( 0, 1, TapisRoulant.DROITE_BAS );
        Vendeur vendeur = new Vendeur( 1, 1 );
        vendeur.placer( 1, 1, usine );

        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.LITHARGE ) );
        assertTrue( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 0, usine.getVente( IdentiteProduit.LITHARGE ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 1, usine.getVente( IdentiteProduit.LITHARGE ) );
        usine.logistique.placerItem( 0, 1, new Produit( IdentiteProduit.CHALCOCITE ) );
        usine.tic();
        assertFalse( usine.logistique.contiensItem( 0, 1 ) );
        assertEquals( 1, usine.getVente( IdentiteProduit.LITHARGE ) );
        assertEquals( 1, usine.getVente( IdentiteProduit.CHALCOCITE ) );
    }
}