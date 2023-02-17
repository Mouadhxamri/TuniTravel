<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Entity\User;
use App\Form\ReclamationType;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\SerializerInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;

/**
 * @Route("/reclamation")
 */
class ReclamationController extends AbstractController
{
    /**
     * @Route("/", name="app_reclamation_index", methods={"GET"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function index(EntityManagerInterface $entityManager , PaginatorInterface $paginator  ,Request $request): Response
    {
        $donnes = $entityManager
            ->getRepository(Reclamation::class)
            ->findAll();
        $reclamations= $paginator->paginate(
            $donnes,
            $request->query->getInt('page',1),
            4
        );

        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamations,
        ]);
    }

    /**
     * @Route("/list", name="list", methods={"GET"})
     */
    public function Reclamations(EntityManagerInterface $entityManager , SerializerInterface $serializer): Response
    {
        $donnes = $entityManager
            ->getRepository(Reclamation::class)
            ->findAll();

        $json=$serializer->serialize($donnes,'json',['groups'=>'reclamation']);
        return new Response(json_encode($json));

    }


    /**
     * @Route("/mesR", name="app_reclamation_list", methods={"GET"})
     * @IsGranted("ROLE_USER")
     */
    public function MesReclamation(EntityManagerInterface $entityManager): Response
    {
        $userId = $this->get('security.token_storage')->getToken()->getUser()->getIdUser();

        $reclamations = $entityManager
            ->getRepository(Reclamation::class)
            ->findBy(array('idUser'=> $userId));

        return $this->render('reclamation/mesReclamations.html.twig', [
            'reclamations' => $reclamations,
        ]);
    }

    /**
     * @Route("/new", name="app_reclamation_new", methods={"GET", "POST"})
     * @IsGranted("ROLE_USER")
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $reclamation = new Reclamation();
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $user = $this->get('security.token_storage')->getToken()->getUser();
            $reclamation->setEtat('Non traite');
            $reclamation->setIdUser($user);

            $reclamation->setDateCreation(new \DateTime() );
            $entityManager->persist($reclamation);
            $entityManager->flush();

            return $this->redirectToRoute('app_reclamation_list', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/stats", name="app_reclamation_stats",methods={"GET","POST"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function statistique(EntityManagerInterface $entityManager ) : Response
    {
       /* $reclamations = $entityManager
            ->getRepository(Reclamation::class)
            ->findAll();
        $typeRec = [];
        $RecCount = [];

  foreach($reclamations as $reclamation){
      $typeRec[]= $reclamation->getTitre();
      $RecCount[]=count($entityManager
          ->getRepository(Reclamation::class)
          ->findBy(array('titre'=> $reclamation->getTitre())));
  }

       */

        $RecCount = [];
        $typeRec = [];
        $a=count($entityManager
            ->getRepository(Reclamation::class)
            ->findBy(array('titre'=> 'reclamationHotel')));

        $b=count($entityManager
            ->getRepository(Reclamation::class)
            ->findBy(array('titre'=> 'reclamationVoiture')));

        $c=count($entityManager
            ->getRepository(Reclamation::class)
            ->findBy(array('titre'=> 'reclamationVol')));

        $d=count($entityManager
            ->getRepository(Reclamation::class)
            ->findBy(array('etat'=> 'Traité')));

        $e=count($entityManager
            ->getRepository(Reclamation::class)
            ->findBy(array('etat'=> 'Non traite')));

        array_push($RecCount, $a, $b,$c);
        array_push($typeRec, $d, $e);
        return $this->render('reclamation/stats.html.twig' ,[
            'typeRec'=> json_encode($typeRec),
            'RecCount'=> json_encode($RecCount)
        ]);
    }

    /**
     * @Route("/show/{id}", name="app_reclamation_show", methods={"GET"})
     * @IsGranted("ROLE_USER")
     */
    public function show(Reclamation $reclamation): Response
    {   $reponse=$reclamation->getReponse();
        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
            'reponse' => $reponse,

        ]);
    }


    /**
     * @Route("/sh/{id}", name="app_reclamation_showD", methods={"GET","POST"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function showD(Reclamation $reclamation): Response
    {   $reponse=$reclamation->getReponse();
        return $this->render('reponse/show.html.twig', [
            'reclamation' => $reclamation,
            'reponse' => $reponse,

        ]);
    }

    /**
     * @Route("/edit/{id}", name="app_reclamation_edit", methods={"GET", "POST"})
     * @IsGranted("ROLE_USER")
     */
    public function edit(Request $request, Reclamation $reclamation, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_reclamation_list', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/delete/{id}", name="app_reclamation_delete", methods={"POST"})
     * @IsGranted("ROLE_USER")
     */
    public function delete(Request $request, Reclamation $reclamation, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getId(), $request->request->get('_token'))   ) {
            $entityManager->remove($reclamation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_reclamation_list', [], Response::HTTP_SEE_OTHER);
    }








}
