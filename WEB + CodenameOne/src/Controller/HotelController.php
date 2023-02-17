<?php

namespace App\Controller;

use App\Entity\Hotel;
use App\Form\HotelType;
use App\Repository\HotelRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Component\HttpFoundation\RequestStack;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\Routing\RouterInterface;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use CMEN\GoogleChartsBundle\GoogleCharts\Options\Histogram;
use Symfony\Component\Validator\Constraints\Json;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;


class HotelController extends AbstractController
{
    /**
     * @Route("/hotel", name="hotel_list")
     * @IsGranted("ROLE_ADMIN")
     */
    public function index(): Response
    {
        $res = $this->getDoctrine()->getManager()->getRepository(hotel::class)->findAll();
        return $this->render('hotel/listhotel.html.twig',array('hotels'=>$res));
      
    }
     /** 
     * @Route("/affichemobilehotel/{id}",name="AfficheMobilerep")
     * @param Request $request
     * @return Response
      */
      public function Affichemobilerep()
      {
         
          $hotel = $this->getDoctrine()->getManager()->getRepository(hotel::class)->findAll();
           $serializer = new Serializer([new ObjectNormalizer()]);
           $formatted = $serializer->normalize($hotel);
  
           return new JsonResponse($formatted);
  
  
      }

    /**
     * @Route("/afficherjson", name="afficherjson")
     */
    public function afficherjson(NormalizerInterface $normalizer)
    {
       
       $hotel=  $this->getDoctrine()->getManager()->getRepository(hotel::class)->findAll();
        $json=$normalizer->normalize($hotel,'json',['groups'=>'post:read']);
        return new Response(json_encode($json));
    }
   /**
     * @Route("/addJSON", name="addJSON")
     */
    public function addJSON(Request $request,NormalizerInterface $normalizer){
        //$content=$request->getContent();
        $em = $this->getDoctrine()->getManager();
        $Hotel= new Hotel();
        $Hotel->setRef($request->get('ref'));
        $Hotel->setNom($request->get('nom'));
        $Hotel->setDescription($request->get('description'));
        $Hotel->setAdresse($request->get('adresse'));
        $Hotel->setPrix($request->get('prix'));
        $Hotel->setImage($request->get('image'));

        $em->persist($Hotel);
        $em->flush();
        $data=$normalizer->normalize($Hotel,'json',['groups'=>'post:read']);

        return new Response(json_encode($data));
    }
/**
     * @Route("/reservationhotel", name="reservationhotel_list")
     * @IsGranted("ROLE_USER")
     */
    public function showh(): Response
    {
        $res = $this->getDoctrine()->getManager()->getRepository(hotel::class)->findAll();
        return $this->render('reservation_hotel/showhotel.html.twig',array('hotels'=>$res));
        
    }
    /**
     * @Route("/addhotel", name="add")
     * @IsGranted("ROLE_ADMIN")
     */
    public function addhotel(\Symfony\Component\HttpFoundation\Request  $request): Response
    {
        $em = $this->getDoctrine()->getManager();
        $hotel = new hotel();
        $form= $this->createForm(hotelType::class,$hotel);

        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()) {

            //upload image
            $uploadFile = $form['image']->getData(); // valeur de l'image
            $filename = md5(uniqid()) . '.' .$uploadFile->guessExtension();//cryptage image

            $uploadFile->move($this->getParameter('kernel.project_dir').'/public/uploads/produit_image',$filename);


            $hotel->setImage($filename);
            /*
             * Add product
             */
            
            $em->persist($hotel);
            /*
             * Commit
             */
            $em->flush();
           
            return  $this->redirectToRoute('hotel_list');
           // $flashy->success('Event created!', 'http://your-awesome-link.com');
        }
       // $flashy->success('Event created!', 'http://your-awesome-link.com');
        return $this->render('hotel/Addhotel.html.twig',array("f"=>$form->createView()));

    }
    /**
    * @Route("/updatehoteljson/{id}/{ref}", name="updatehoteljson")
    * @Method("PUT")
    */
   public function updatehoteljson(Request $request) {
    $em = $this->getDoctrine()->getManager();
    $hotel = $this->getDoctrine()->getManager()->getRepository(hotel::class)->find($request->get("ref"));
    $hotel->setRef($request->get("ref"));
    $hotel->setNom($request->get("nom"));
    $hotel->setDescription($request->get("description"));
    $hotel->setAdresse($request->get("adresse"));
    $hotel->setPrix($request->get("prix"));
    $hotel->setImage($request->get("image"));

    $em->persist($hotel);
    $em->flush();
    $serializer = new Serializer([new ObjectNormalizer()]);
    $formatted = $serializer->normalize($hotel);
    return new JsonResponse("hotel a ete modifiee avec success.");

}
    /**
     * @Route("/editHotel/{id}", name="edit_hotel")
     * Method({"GET", "POST"})
     * @IsGranted("ROLE_ADMIN")
     */
    public function edit(\Symfony\Component\HttpFoundation\Request  $request, $id) {
        $hotel = new hotel();
        $hotel = $this->getDoctrine()->getRepository(hotel::class)->find($id);
        $em= $this->getDoctrine()->getManager();
        $hotel = $em->getRepository(hotel::class)->find($id);
        $form = $this->createForm(hotelType::class,$hotel);
        $form->handleRequest($request);

        if($form->isSubmitted() && $form->isValid()) {
            $em->flush();

            return $this->redirectToRoute('hotel_list');

        }

        return $this->render('hotel/edit.html.twig',array("f"=>$form->createView()));
    }
 /**
      * @Route("/deletehoteljson/{id}/{ref}", name="deletehoteljson")
      * @Method("DELETE")
      */

      public function deletehoteljson(Request $request) {
        $ref = $request->get("ref");

        $em = $this->getDoctrine()->getManager();
        $hotel = $em->getRepository(hotel::class)->find($ref);
        if($hotel!=null ) {
            $em->remove($hotel);
            $em->flush();

            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("hotel a ete supprimee avec success.");
            return new JsonResponse($formatted);

        }
        return new JsonResponse("id hotel invalide.");


    }
  /**
     * @Route("/deletehotel/{id}",name="delete_hotel")
     * @IsGranted("ROLE_ADMIN")
     */
    public function delete(Request $request, $id) {
       
        $hotel= $this->getDoctrine()->getRepository(hotel::class)->find($id);

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($hotel);
        $entityManager->flush();

        $response = new Response();
        $response->send();
        return $this->redirectToRoute('hotel_list');
    }
    /**
     * @Route("/detailhotel/{id}", name="detail")
     * @IsGranted("ROLE_ADMIN")
     */
    public function detailhotel(\Symfony\Component\HttpFoundation\Request $req, $id)
    {
        $em = $this->getDoctrine()->getManager();
        $hotel = $em->getRepository(hotel::class)->find($id);
    
        return $this->render('hotel/detailhotel.html.twig', array(
            'id' => $hotel->getId(),
            'ref' => $hotel->getRef(),
            'nom' => $hotel->getNom(),
            'description' => $hotel->getDescription(),
            'adresse' => $hotel->getAdresse(),
            'prix' => $hotel->getprix(),
            'image'=>$hotel->getImage()
            

        ));
    }
    //SEARCH

    /**
     * @Route("/ajax_searchhotel/", name="ajax_searchhotel")
     */
    public function chercherHotel(\Symfony\Component\HttpFoundation\Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');// ooofkdokfdfdf
        $Hotels =  $em->getRepository(Hotel::class)->rechercheAvance($requestString);
        if(!$Hotels) {
            $result['hotels']['error'] = "Hotel non trouvé :( ";
        } else {
            $result['hotels'] = $this->getRealEntities($Hotels);
        }
        return new Response(json_encode($result));
    }
    public function getRealEntities($hotels){
        foreach ($hotels as $hotels){
            $realEntities[$hotels->getId()] = [$hotels->getRef(),$hotels->getNom(),$hotels->getDescription(),$hotels->getAdresse(),$hotels->getPrix(),$hotels->getImage(),];

        }
        return $realEntities;
    }
  
    
    /**
     * @Route("/stat1", name="stat1")
     * @IsGranted("ROLE_ADMIN")
     */
    public function indexAction(){
        $repository = $this->getDoctrine()->getRepository(Hotel::class);
        $hotels = $repository->findAll();
        
        $rd=0;
        $qu=0;
        $qy=0;
        $qm=0;
           foreach ($hotels as $hotels)
        {
            if ($hotels->getAdresse()=='Tunis')  :
                $rd+=1;
            elseif($hotels->getAdresse()=='Sousse') :
                $qu+=1;
                elseif($hotels->getAdresse()=='Hammamet') :
                    $qm+=1;
            else:
                $qy+=1;
            endif;
        }

        $pieChart = new PieChart();
        $pieChart->getData()->setArrayToDataTable(
            [['', ''],
                ['tunis',     $rd],
                ['sousse',      $qu],
                ['kef',      $qy],
                ['Hammamet',      $qm],
               
               
            ]
        );
        $pieChart->getOptions()->setTitle('Statistiques des Hotels par Ville');
        $pieChart->getOptions()->setHeight(500);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(20);

        $hotels1 = $repository->findAll();
        $rd1=0;
        $qu1=0;
        $qy1=0;
        $qm1=0;
           foreach ($hotels1 as $hotels1)
        {
            if (($hotels1->getPrix()>100)&($hotels1->getPrix()<500))  :
                $rd1+=1;
            elseif(($hotels1->getPrix()>500)&($hotels1->getPrix()<1000)) :
                $qu1+=1;
                elseif(($hotels1->getPrix()>1000)&($hotels1->getPrix()<1500)) :
                    $qm1+=1;
            else:
                $qy1+=1;
            endif;
        }
        $pieChart1 = new PieChart();
        $pieChart1->getData()->setArrayToDataTable(
            [['', ''],
                ['Entre 100DT et 500DT',     $rd1],
                ['Entre 500DT et 1000DT',      $qu1],
                ['Entre 1000DT et 1500DT',      $qy1],
                ['>2000DT',      $qm1],
               
               
            ]
        );
        $pieChart1->getOptions()->setTitle('Statistiques des Hotels par Prix');
        $pieChart1->getOptions()->setHeight(600);
        $pieChart1->getOptions()->setWidth(1000);
        $pieChart1->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart1->getOptions()->getTitleTextStyle()->setColor('#990035');
        $pieChart1->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart1->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart1->getOptions()->getTitleTextStyle()->setFontSize(25);


        return $this->render('hotel/stat.html.twig', array('piechart' => $pieChart, 'piechart1' => $pieChart1));
    }



}
