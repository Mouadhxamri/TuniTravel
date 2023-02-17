<?php

namespace App\Form;

use App\Entity\ReservationHotel;
use App\Entity\Hotel;
use App\Entity\Chambre;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Validator\Constraints;
use Symfony\Component\Validator\Context\ExecutionContextInterface;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Validator\Constraints\GreaterThan;
use Symfony\Component\Validator\Constraints\NotBlank;
use Symfony\Component\Validator\Constraints\Date;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
class ReservationHotelType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('id_user', TextType::class, [
                'label' => '4'
            ])
            ->add('date_debut', DateType::class,array( 'days' => range(date('d') , date('30'))) )
       /*    ->add('date_debut', DateType::class, [
            'constraints' => [
                new NotBlank(),
                new Date(),
                new GreaterThan([
                    'propertyPath' => 'parent.all[date_fin].data'
                ]),
            ]
        ])*/
         /*  ->add('date_fin', DateType::class ,[
            'constraints' => [
                new NotBlank(),
                new Date(), ]
                ])*/
            ->add('date_fin', DateType::class)
           // ->add('id_hotel')
            ->add('nbrperso', ChoiceType::class, [
                'choices'  => [
                    '1' => "1",
                    '2' => "2",
                ],
            ])
            ->add('id_chambre')
            ->add('prixtotal')
            ->add('idhotel')
            ->add('Submit',SubmitType::class)
            
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => ReservationHotel::class,
        ]);
    }
}
