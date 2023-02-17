<?php

namespace App\Controller;

use App\Entity\Vol;
use App\Form\VolType;
use Doctrine\ORM\EntityManagerInterface;
use App\Repository\VolRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Validator\Constraints\Json;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;



use Swift_Mailer;
use Swift_Message;

/**
 * @Route("/vol")
 */
class VolController extends AbstractController
{
    /**
     * @Route("/stat", name="stat")
     * @IsGranted("ROLE_ADMIN")
     */
    public function indexAction(){
        $repository = $this->getDoctrine()->getRepository(Vol::class);
        $vols = $repository->findAll();
        $vols1 = $repository->findAll();

        $rd=0;
        $qu=0;
        $qy=0;
        $qm=0;
        $q1=0;
        $q2=0;
        $q3=0;
        $q4=0;
           foreach ($vols as $vols)
        {
            if ($vols->getDepart()=='Tunis')  :
                $rd+=1;
            elseif($vols->getDepart()=='Amsterdam') :
                $qu+=1;
                elseif($vols->getDepart()=='Berlin') :
                    $qm+=1;
            else:
                $qy+=1;
            endif;
        }
        

        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable(
            [['', ''],
                ['Tunis',     $rd],
                ['Amsterdam',      $qu],
                ['Berlin',      $qy],
                ['Paris',      $qm],
               
               
            ]
        );
        
        $pieChart->getOptions()->setTitle('Statistiques des Vols par Départ');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);

        foreach ($vols1 as $vols1)
        {
            if ($vols1->getDestination()=='Tunis')  :
                $q1+=1;
            elseif($vols1->getDestination()=='Cairo') :
                $q2+=1;
                elseif($vols1->getDestination()=='Djerba') :
                    $q3+=1;
            else:
                $q4+=1;
            endif;
        }
        $pieChart1 = new PieChart();
        $pieChart1->getData()->setArrayToDataTable(
            [['', ''],
                ['Tunis',     $q1],
                ['Cairo',      $q2],
                ['Djerba',      $q3],
                ['Algérie',      $q4],
               
               
            ]
        );
        $pieChart1->getOptions()->setTitle('Statistiques des Vols par Destination');
        $pieChart1->getOptions()->setHeight(500);
        $pieChart1->getOptions()->setWidth(900);
        $pieChart1->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart1->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart1->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart1->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart1->getOptions()->getTitleTextStyle()->setFontSize(20);

        return $this->render('vol/stat.html.twig', array('piechart' => $pieChart , 'piechart1' => $pieChart1));
    }



    /**
     * @Route("/", name="app_vol_index", methods={"GET"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $vols = $entityManager
            ->getRepository(Vol::class)
            ->findAll();

        return $this->render('vol/index.html.twig', [
            'vols' => $vols,
        ]);
    }
 /**
     * @Route("/showvol", name="appshowvol", methods={"GET"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function index1(EntityManagerInterface $entityManager): Response
    {
        $vols = $entityManager
            ->getRepository(Vol::class)
            ->findAll();

          //  $vol = $this->getDoctrine()->getManager()->getRepository(Vol::class)->findAll();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($vols);
   
            return new JsonResponse($formatted);
    }
    /**
     * @Route("/new", name="app_vol_new", methods={"GET", "POST"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $vol = new Vol();
        $form = $this->createForm(VolType::class, $vol);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($vol);
            $entityManager->flush();

            return $this->redirectToRoute('app_vol_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('vol/new.html.twig', [
            'vol' => $vol,
            'form' => $form->createView(),
        ]);
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($vol);
        return new JsonResponse($formatted);
    }
/**
     * @Route("/addJSON", name="app_volnewjson", methods={"GET", "POST"})
     */
    public function addJSON(Request $request, EntityManagerInterface $entityManager,NormalizerInterface $Normalizer): Response
    {
        $vol = new Vol();
        $form = $this->createForm(VolType::class, $vol);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($vol);
            $entityManager->flush();

            return $this->redirectToRoute('app_vol_index', [], Response::HTTP_SEE_OTHER);
        }

        $jsonContent=$Normalizer->normalize($vol, 'json',['groups'=>'post:read']);

return new Response(json_encode($jsonContent));
    }

    /**
     * @Route("/ajax_search/", name="ajax_search")
     */
    public function chercherVol(\Symfony\Component\HttpFoundation\Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');// ooofkdokfdfdf
        $Vols =  $em->getRepository(Vol::class)->rechercheAvance($requestString);
        if(!$Vols) {
            $result['vols']['error'] = "Vol non trouvé 🙁 ";
        } else {
            $result['vols'] = $this->getRealEntities($Vols);
        }
        return new Response(json_encode($result));
    }
    public function getRealEntities($vols){
        foreach ($vols as $vols){
            $realEntities[$vols->getIdVol()] = [$vols->getRef(),$vols->getDepart(),$vols->getDestination(),$vols->getDateD(),$vols->getDateR()];

        }
        return $realEntities;
    }

    /**
     * @Route("/{idVol}", name="app_vol_show", methods={"GET"})
     */
    public function show(Vol $vol): Response
    {
        return $this->render('vol/show.html.twig', [
            'vol' => $vol,
        ]);
    }

    /**
     * @Route("/{idVol}/edit", name="app_vol_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Vol $vol, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(VolType::class, $vol);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_vol_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('vol/edit.html.twig', [
            'vol' => $vol,
            'form' => $form->createView(),
        ]);
    }

    

    /**
     * @Route("/{idVol}", name="app_vol_delete", methods={"POST"})
     */
    public function delete(Request $request, Vol $vol, EntityManagerInterface $entityManager,\Swift_Mailer $mailer): Response
    {
        if ($this->isCsrfTokenValid('delete'.$vol->getIdVol(), $request->request->get('_token'))) {
            $entityManager->remove($vol);
            $entityManager->flush();
        }
        $message = (new \Swift_Message('Suppression'))
        // On attribue l'expéditeur
        ->setFrom('tunitraveltech@gmail.com')
        // On attribue le destinataire
        ->setTo('yosri.jedidi@esprit.tn')
        ->setBody(
         "Vol supprimé!"
             
         )
         
     
 ;

 $mailer->send($message);
        return $this->redirectToRoute('app_vol_index', [], Response::HTTP_SEE_OTHER);
    }

 

/** 
     * @route("/affichemobilevol/{id}",name="AfficheMobilerep")
     * @param Request $request
     * @return Response

      */
      public function Affichemobilerep()
      {
         
           $vol = $this->getDoctrine()->getManager()->getRepository(Vol::class)->findAll();
           $serializer = new Serializer([new ObjectNormalizer()]);
           $formatted = $serializer->normalize($vol);
  
           return new JsonResponse($formatted);
  
  
      }
      /**
        * @Route("/addVol/{id}", name="add_Vol")
        * @Method("POST")
        */
  
        public function ajouterVolAction(Request $request)
        {
            $vol = new Vol();
            $idVol = $request->query->get("idVol");
            $ref = $request->query->get("ref");
            $depart = $request->query->get("depart");
            $destination = $request->query->get("destination");
         //   $dateD = $request->query->get("dateD");
         //   $dateR = $request->query->get("dateR");
            $prixV = $request->query->get("prixV");
            $em = $this->getDoctrine()->getManager();
            //$date = new \DateTime('now');
 //  $TM = strtotime($dateD);
 //  $DR = date('Y-m-d',$TM);
   
            $vol->setIdVol($idVol);
            $vol->setRef($ref);
            $vol->setDepart($depart);
            $vol->setDestination($destination);
         //   $vol->setDateD($dateD);
         //   $vol->setDateR($dateR);
            $vol->setPrixV($prixV);
   
            $em->persist($vol);
            $em->flush();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($vol);

            return new JsonResponse($formatted);
   
        }
         /**
        * @Route("/deletevol/{id}/{ref}", name="delete_vol")
        * @Method("DELETE")
        */
  
       public function deletevolAction(Request $request) {
          $ref = $request->get("ref");
  
          $em = $this->getDoctrine()->getManager();
          $vol = $em->getRepository(Vol::class)->find($ref);
          if($vol!=null ) {
              $em->remove($vol);
              $em->flush();
  
              $serialize = new Serializer([new ObjectNormalizer()]);
              $formatted = $serialize->normalize("vol a ete supprimee avec success.");
              return new JsonResponse($formatted);
  
          }
          return new JsonResponse("id vol invalide.");
  
  
      }
  
     /******************Modifier vol*****************************************/
     /**
      * @Route("/updatevol/{id}/{ref}", name="update_vol")
      * @Method("PUT")
      */
     public function modifiervolAction(Request $request) {
         $em = $this->getDoctrine()->getManager();
         $vol = $this->getDoctrine()->getManager()->getRepository(Vol::class)->find($request->get("ref"));
         $vol->setDepart($request->get("depart"));
         $vol->setDestination($request->get("destination"));
         $vol->setDateD($request->get("dateD"));
         $vol->setDateR($request->get("dateR"));
         $vol->setIdVol($request->get("idVol"));
         $vol->setPrixV($request->get("prixV"));
  
  
  
         $em->persist($vol);
         $em->flush();
         $serializer = new Serializer([new ObjectNormalizer()]);
         $formatted = $serializer->normalize($vol);
         return new JsonResponse("vol a ete modifiee avec success.");
  
     }


}
